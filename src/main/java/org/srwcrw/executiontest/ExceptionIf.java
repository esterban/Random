package org.srwcrw.executiontest;

//import lombok.extern.slf4j.Slf4j;

import org.HdrHistogram.Histogram;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.function.Function;

//@Slf4j
public class ExceptionIf {
    //    private static final Logger log =
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ExceptionIf.class);
    private static Random random;

    private static final Exception exception = new Exception();

    private static Histogram histogram = new Histogram(5);

    public static void main(String[] args) {
        random = new Random();

//        for (int outerCounter = 1; outerCounter <= 100; ++outerCounter) {
//            long branchCount = 0;
//
//            long startNanos = System.nanoTime();
//            for (int counter = 1; counter < 1e6; ++counter) {
//                branchCount = funcIf(branchCount);
////                branchCount = funcException(branchCount);
//            }
//
//            long endNanos = System.nanoTime();
//            long duration = endNanos - startNanos;
//            double durationMS = duration / 1e6;
//
//            histogram.recordConvertedDoubleValueWithCount(durationMS, 1);
//        }

        ExceptionIf exceptionIf = new ExceptionIf();

        for (int testCounter = 1; testCounter <= 10; ++testCounter) {
            performTest(exceptionIf::funcIf, "funcIf");
            performTest(exceptionIf::funcException, "funcException");
        }


//        LOGGER.info("Mean {}, SD {}", histogram.getMean(), histogram.getStdDeviation());
    }

    private static void performTest(Function<Long, Long> ifFunc, String testName) {
        for (int outerCounter = 1; outerCounter <= 100; ++outerCounter) {
            long branchCount = 0;

            long startNanos = System.nanoTime();
            for (int counter = 1; counter < 1e6; ++counter) {
//                branchCount = funcIf(branchCount);

                branchCount = ifFunc.apply(branchCount);
//                branchCount = funcException(branchCount);
            }

            long endNanos = System.nanoTime();
            long duration = endNanos - startNanos;
            double durationMS = duration / 1e6;

            histogram.recordConvertedDoubleValueWithCount(durationMS, 1);
        }

        LOGGER.info("Function name is {}, Mean {}, SD {}", testName, histogram.getMean(), histogram.getStdDeviation());
    }

    private long funcIf(long branchCount) {
        return ++branchCount;
    }

//    private long funcIf(long branchCount) {
//        if (random.nextBoolean()) {
//            ++branchCount;
//        }
//
//        return branchCount;
//    }

    private long funcException(long branchCount) {
        try {
            if (random.nextBoolean()) {
                throw exception;
            }
        } catch (Exception e) {
            ++branchCount;
        }


        if (random.nextBoolean()) {
            ++branchCount;
        }

        return branchCount;
    }

}
