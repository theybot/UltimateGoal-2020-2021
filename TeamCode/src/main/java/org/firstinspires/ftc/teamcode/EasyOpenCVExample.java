package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;


@TeleOp
public class EasyOpenCVExample extends LinearOpMode
    {
        OpenCvCamera webcam;
        SkystoneDeterminationPipeline pipeline;

    @Override
    public void runOpMode()
    {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        pipeline = new SkystoneDeterminationPipeline();
        webcam.setPipeline(new EasyOpenCVExample.SamplePipeline());

//        webcam.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                webcam.startStreaming(320, 240, OpenCvCameraRotation.SIDEWAYS_LEFT);
            }
        });

        waitForStart();

        while (opModeIsActive())
        {
            webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            telemetry.addData("Analysis", pipeline.getAnalysis());
            telemetry.addData("Position", pipeline.position);
            telemetry.update();

            // Don't burn CPU cycles busy-looping in this sample
            sleep(50);
        }
    }

    public static class SkystoneDeterminationPipeline extends OpenCvPipeline
    {
        /*
         * An enum to define the skystone position
         */
        public enum RingPosition
        {
            FOUR,
            ONE,
            NONE
        }

        /*
         * Some color constants
         */
        static final Scalar BLUE = new Scalar(0, 0, 255);
        static final Scalar GREEN = new Scalar(0, 255, 0);

        /*
         * The core values which define the location and size of the sample regions
         */
        static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(181,98);

        static final int REGION_WIDTH = 35;
        static final int REGION_HEIGHT = 25;

        final int FOUR_RING_THRESHOLD =1000;
        final int ONE_RING_THRESHOLD = 900;

        Point region1_pointA = new Point(
                REGION1_TOPLEFT_ANCHOR_POINT.x,
                REGION1_TOPLEFT_ANCHOR_POINT.y);
        Point region1_pointB = new Point(
                REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
                REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

        /*
         * Working variables
         */
        Mat region1_Cb;
        Mat YCrCb = new Mat();
        Mat Cb = new Mat();
        int avg1;

        // Volatile since accessed by OpMode thread w/o synchronization
        private volatile RingPosition position = RingPosition.NONE;

        /*
         * This function takes the RGB frame, converts to YCrCb,
         * and extracts the Cb channel to the 'Cb' variable
         */
        void inputToCb(Mat input)
        {
            Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(YCrCb, Cb, 1);
        }

        @Override
        public void init(Mat firstFrame)
        {
            inputToCb(firstFrame);

            region1_Cb = Cb.submat(new Rect(region1_pointA, region1_pointB));
        }

        @Override
        public Mat processFrame(Mat input)
        {
            inputToCb(input);

            avg1 = (int) Core.mean(region1_Cb).val[0];

            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    BLUE, // The color the rectangle is drawn in
                    2); // Thickness of the rectangle lines

            position = RingPosition.FOUR; // Record our analysis
            if(avg1 > FOUR_RING_THRESHOLD){
                position = RingPosition.FOUR;
            }else if (avg1 > ONE_RING_THRESHOLD){
                position = RingPosition.ONE;
            }else{
                position = RingPosition.NONE;
            }

            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    GREEN, // The color the rectangle is drawn in
                    -1); // Negative thickness means solid fill

            return input;
        }

        public int getAnalysis()
        {
            return avg1;
        }
    }
        class SamplePipeline extends OpenCvPipeline
        {
            boolean viewportPaused;

            /*
             * NOTE: if you wish to use additional Mat objects in your processing pipeline, it is
             * highly recommended to declare them here as instance variables and re-use them for
             * each invocation of processFrame(), rather than declaring them as new local variables
             * each time through processFrame(). This removes the danger of causing a memory leak
             * by forgetting to call mat.release(), and it also reduces memory pressure by not
             * constantly allocating and freeing large chunks of memory.
             */

            @Override
            public Mat processFrame(Mat input)
            {
                /*
                 * IMPORTANT NOTE: the input Mat that is passed in as a parameter to this method
                 * will only dereference to the same image for the duration of this particular
                 * invocation of this method. That is, if for some reason you'd like to save a copy
                 * of this particular frame for later use, you will need to either clone it or copy
                 * it to another Mat.
                 */

                /*
                 * Draw a simple box around the middle 1/2 of the entire frame
                 */
                Imgproc.rectangle(
                        input,
                        new Point(
                                input.cols()/4,
                                input.rows()/4),
                        new Point(
                                input.cols()*(3f/4f),
                                input.rows()*(3f/4f)),
                        new Scalar(0, 255, 0), 4);

                /**
                 * NOTE: to see how to get data from your pipeline to your OpMode as well as how
                 * to change which stage of the pipeline is rendered to the viewport when it is
                 * tapped, please see {@link PipelineStageSwitchingExample}
                 */

                return input;
            }

            @Override
            public void onViewportTapped()
            {
                /*
                 * The viewport (if one was specified in the constructor) can also be dynamically "paused"
                 * and "resumed". The primary use case of this is to reduce CPU, memory, and power load
                 * when you need your vision pipeline running, but do not require a live preview on the
                 * robot controller screen. For instance, this could be useful if you wish to see the live
                 * camera preview as you are initializing your robot, but you no longer require the live
                 * preview after you have finished your initialization process; pausing the viewport does
                 * not stop running your pipeline.
                 *
                 * Here we demonstrate dynamically pausing/resuming the viewport when the user taps it
                 */

                viewportPaused = !viewportPaused;

                if(viewportPaused)
                {
                    webcam.pauseViewport();
                }
                else
                {
                    webcam.resumeViewport();
                }
          }
        }
}
