package code.person.util;

/**
 * <p>Title: bank viso</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author chengcai
 * @version 1.0
 */

//des¼ÓÃÜËã·¨Àà
public class Descrypt
{

    int NN;
    final long BIT1 = 0x80000000L;
    final long BIT2 = 0x40000000L;
    final long BIT3 = 0x20000000L;
    final long BIT4 = 0x10000000L;
    final long BIT5 = 0x8000000L;
    final long BIT6 = 0x4000000L;
    final long BIT7 = 0x2000000L;
    final long BIT8 = 0x1000000L;
    final long BIT9 = 0x800000L;
    final long BIT10 = 0x400000L;
    final long BIT11 = 0x200000L;
    final long BIT12 = 0x100000L;
    final long BIT13 = 0x80000L;
    final long BIT14 = 0x40000L;
    final long BIT15 = 0x20000L;
    final long BIT16 = 0x10000L;
    final long BIT17 = 32768L;
    final long BIT18 = 16384L;
    final long BIT19 = 8192L;
    final long BIT20 = 4096L;
    final long BIT21 = 2048L;
    final long BIT22 = 1024L;
    final long BIT23 = 512L;
    final long BIT24 = 256L;
    final long BIT25 = 128L;
    final long BIT26 = 64L;
    final long BIT27 = 32L;
    final long BIT28 = 16L;
    final long BIT29 = 8L;
    final long BIT30 = 4L;
    final long BIT31 = 2L;
    final long BIT32 = 1L;
    static long S1[][] = {
        {
            14L, 4L, 13L, 1L, 2L, 15L, 11L, 8L, 3L, 10L,
            6L, 12L, 5L, 9L, 0, 7L, 0, 15L, 7L, 4L,
            14L, 2L, 13L, 1L, 10L, 6L, 12L, 11L, 9L, 5L,
            3L, 8L, 4L, 1L, 14L, 8L, 13L, 6L, 2L, 11L,
            15L, 12L, 9L, 7L, 3L, 10L, 5L, 0, 15L, 12L,
            8L, 2L, 4L, 9L, 1L, 7L, 5L, 11L, 3L, 14L,
            10L, 0, 6L, 13L
        }, {
            0, 15L, 7L, 4L, 14L, 2L, 13L, 1L, 10L, 6L,
            12L, 11L, 9L, 5L, 3L, 8L, 4L, 1L, 14L, 8L,
            13L, 6L, 2L, 11L, 15L, 12L, 9L, 7L, 3L, 10L,
            5L, 0, 15L, 12L, 8L, 2L, 4L, 9L, 1L, 7L,
            5L, 11L, 3L, 14L, 10L, 0, 6L, 13L, 15L, 1L,
            8L, 14L, 6L, 11L, 3L, 4L, 9L, 7L, 2L, 13L,
            12L, 0, 5L, 10L
        }, {
            4L, 1L, 14L, 8L, 13L, 6L, 2L, 11L, 15L, 12L,
            9L, 7L, 3L, 10L, 5L, 0, 15L, 12L, 8L, 2L,
            4L, 9L, 1L, 7L, 5L, 11L, 3L, 14L, 10L, 0,
            6L, 13L, 15L, 1L, 8L, 14L, 6L, 11L, 3L, 4L,
            9L, 7L, 2L, 13L, 12L, 0, 5L, 10L, 3L, 13L,
            4L, 7L, 15L, 2L, 8L, 14L, 12L, 0, 1L, 10L,
            6L, 9L, 11L, 5L
        }, {
            15L, 12L, 8L, 2L, 4L, 9L, 1L, 7L, 5L, 11L,
            3L, 14L, 10L, 0, 6L, 13L, 15L, 1L, 8L, 14L,
            6L, 11L, 3L, 4L, 9L, 7L, 2L, 13L, 12L, 0,
            5L, 10L, 3L, 13L, 4L, 7L, 15L, 2L, 8L, 14L,
            12L, 0, 1L, 10L, 6L, 9L, 11L, 5L, 0, 14L,
            7L, 11L, 10L, 4L, 13L, 1L, 5L, 8L, 12L, 6L,
            9L, 3L, 2L, 15L
        }
    };
    static long S2[][] = {
        {
            15L, 1L, 8L, 14L, 6L, 11L, 3L, 4L, 9L, 7L,
            2L, 13L, 12L, 0, 5L, 10L, 3L, 13L, 4L, 7L,
            15L, 2L, 8L, 14L, 12L, 0, 1L, 10L, 6L, 9L,
            11L, 5L, 0, 14L, 7L, 11L, 10L, 4L, 13L, 1L,
            5L, 8L, 12L, 6L, 9L, 3L, 2L, 15L, 13L, 8L,
            10L, 1L, 3L, 15L, 4L, 2L, 11L, 6L, 7L, 12L,
            0, 5L, 14L, 9L
        }, {
            3L, 13L, 4L, 7L, 15L, 2L, 8L, 14L, 12L, 0,
            1L, 10L, 6L, 9L, 11L, 5L, 0, 14L, 7L, 11L,
            10L, 4L, 13L, 1L, 5L, 8L, 12L, 6L, 9L, 3L,
            2L, 15L, 13L, 8L, 10L, 1L, 3L, 15L, 4L, 2L,
            11L, 6L, 7L, 12L, 0, 5L, 14L, 9L, 10L, 0,
            9L, 14L, 6L, 3L, 15L, 5L, 1L, 13L, 12L, 7L,
            11L, 4L, 2L, 8L
        }, {
            0, 14L, 7L, 11L, 10L, 4L, 13L, 1L, 5L, 8L,
            12L, 6L, 9L, 3L, 2L, 15L, 13L, 8L, 10L, 1L,
            3L, 15L, 4L, 2L, 11L, 6L, 7L, 12L, 0, 5L,
            14L, 9L, 10L, 0, 9L, 14L, 6L, 3L, 15L, 5L,
            1L, 13L, 12L, 7L, 11L, 4L, 2L, 8L, 13L, 7L,
            0, 9L, 3L, 4L, 6L, 10L, 2L, 8L, 5L, 14L,
            12L, 11L, 15L, 1L
        }, {
            13L, 8L, 10L, 1L, 3L, 15L, 4L, 2L, 11L, 6L,
            7L, 12L, 0, 5L, 14L, 9L, 10L, 0, 9L, 14L,
            6L, 3L, 15L, 5L, 1L, 13L, 12L, 7L, 11L, 4L,
            2L, 8L, 13L, 7L, 0, 9L, 3L, 4L, 6L, 10L,
            2L, 8L, 5L, 14L, 12L, 11L, 15L, 1L, 13L, 6L,
            4L, 9L, 8L, 15L, 3L, 0, 11L, 1L, 2L, 12L,
            5L, 10L, 14L, 7L
        }
    };
    static long S3[][] = {
        {
            10L, 0, 9L, 14L, 6L, 3L, 15L, 5L, 1L, 13L,
            12L, 7L, 11L, 4L, 2L, 8L, 13L, 7L, 0, 9L,
            3L, 4L, 6L, 10L, 2L, 8L, 5L, 14L, 12L, 11L,
            15L, 1L, 13L, 6L, 4L, 9L, 8L, 15L, 3L, 0,
            11L, 1L, 2L, 12L, 5L, 10L, 14L, 7L, 1L, 10L,
            13L, 0, 6L, 9L, 8L, 7L, 4L, 15L, 14L, 3L,
            11L, 5L, 2L, 12L
        }, {
            13L, 7L, 0, 9L, 3L, 4L, 6L, 10L, 2L, 8L,
            5L, 14L, 12L, 11L, 15L, 1L, 13L, 6L, 4L, 9L,
            8L, 15L, 3L, 0, 11L, 1L, 2L, 12L, 5L, 10L,
            14L, 7L, 1L, 10L, 13L, 0, 6L, 9L, 8L, 7L,
            4L, 15L, 14L, 3L, 11L, 5L, 2L, 12L, 7L, 13L,
            14L, 3L, 0, 6L, 9L, 10L, 1L, 2L, 8L, 5L,
            11L, 12L, 4L, 15L
        }, {
            13L, 6L, 4L, 9L, 8L, 15L, 3L, 0, 11L, 1L,
            2L, 12L, 5L, 10L, 14L, 7L, 1L, 10L, 13L, 0,
            6L, 9L, 8L, 7L, 4L, 15L, 14L, 3L, 11L, 5L,
            2L, 12L, 7L, 13L, 14L, 3L, 0, 6L, 9L, 10L,
            1L, 2L, 8L, 5L, 11L, 12L, 4L, 15L, 13L, 8L,
            11L, 5L, 6L, 15L, 0, 3L, 4L, 7L, 2L, 12L,
            1L, 10L, 14L, 9L
        }, {
            1L, 10L, 13L, 0, 6L, 9L, 8L, 7L, 4L, 15L,
            14L, 3L, 11L, 5L, 2L, 12L, 7L, 13L, 14L, 3L,
            0, 6L, 9L, 10L, 1L, 2L, 8L, 5L, 11L, 12L,
            4L, 15L, 13L, 8L, 11L, 5L, 6L, 15L, 0, 3L,
            4L, 7L, 2L, 12L, 1L, 10L, 14L, 9L, 10L, 6L,
            9L, 0, 12L, 11L, 7L, 13L, 15L, 1L, 3L, 14L,
            5L, 2L, 8L, 4L
        }
    };
    static long S4[][] = {
        {
            7L, 13L, 14L, 3L, 0, 6L, 9L, 10L, 1L, 2L,
            8L, 5L, 11L, 12L, 4L, 15L, 13L, 8L, 11L, 5L,
            6L, 15L, 0, 3L, 4L, 7L, 2L, 12L, 1L, 10L,
            14L, 9L, 10L, 6L, 9L, 0, 12L, 11L, 7L, 13L,
            15L, 1L, 3L, 14L, 5L, 2L, 8L, 4L, 3L, 15L,
            0, 6L, 10L, 1L, 13L, 8L, 9L, 4L, 5L, 11L,
            12L, 7L, 2L, 14L
        }, {
            13L, 8L, 11L, 5L, 6L, 15L, 0, 3L, 4L, 7L,
            2L, 12L, 1L, 10L, 14L, 9L, 10L, 6L, 9L, 0,
            12L, 11L, 7L, 13L, 15L, 1L, 3L, 14L, 5L, 2L,
            8L, 4L, 3L, 15L, 0, 6L, 10L, 1L, 13L, 8L,
            9L, 4L, 5L, 11L, 12L, 7L, 2L, 14L, 2L, 12L,
            4L, 1L, 7L, 10L, 11L, 6L, 8L, 5L, 3L, 15L,
            13L, 0, 14L, 9L
        }, {
            10L, 6L, 9L, 0, 12L, 11L, 7L, 13L, 15L, 1L,
            3L, 14L, 5L, 2L, 8L, 4L, 3L, 15L, 0, 6L,
            10L, 1L, 13L, 8L, 9L, 4L, 5L, 11L, 12L, 7L,
            2L, 14L, 2L, 12L, 4L, 1L, 7L, 10L, 11L, 6L,
            8L, 5L, 3L, 15L, 13L, 0, 14L, 9L, 14L, 11L,
            2L, 12L, 4L, 7L, 13L, 1L, 5L, 0, 15L, 10L,
            3L, 9L, 8L, 6L
        }, {
            3L, 15L, 0, 6L, 10L, 1L, 13L, 8L, 9L, 4L,
            5L, 11L, 12L, 7L, 2L, 14L, 2L, 12L, 4L, 1L,
            7L, 10L, 11L, 6L, 8L, 5L, 3L, 15L, 13L, 0,
            14L, 9L, 14L, 11L, 2L, 12L, 4L, 7L, 13L, 1L,
            5L, 0, 15L, 10L, 3L, 9L, 8L, 6L, 4L, 2L,
            1L, 11L, 10L, 13L, 7L, 8L, 15L, 9L, 12L, 5L,
            6L, 3L, 0, 14L
        }
    };
    static long S5[][] = {
        {
            2L, 12L, 4L, 1L, 7L, 10L, 11L, 6L, 8L, 5L,
            3L, 15L, 13L, 0, 14L, 9L, 14L, 11L, 2L, 12L,
            4L, 7L, 13L, 1L, 5L, 0, 15L, 10L, 3L, 9L,
            8L, 6L, 4L, 2L, 1L, 11L, 10L, 13L, 7L, 8L,
            15L, 9L, 12L, 5L, 6L, 3L, 0, 14L, 11L, 8L,
            12L, 7L, 1L, 14L, 2L, 13L, 6L, 15L, 0, 9L,
            10L, 4L, 5L, 3L
        }, {
            14L, 11L, 2L, 12L, 4L, 7L, 13L, 1L, 5L, 0,
            15L, 10L, 3L, 9L, 8L, 6L, 4L, 2L, 1L, 11L,
            10L, 13L, 7L, 8L, 15L, 9L, 12L, 5L, 6L, 3L,
            0, 14L, 11L, 8L, 12L, 7L, 1L, 14L, 2L, 13L,
            6L, 15L, 0, 9L, 10L, 4L, 5L, 3L, 12L, 1L,
            10L, 15L, 9L, 2L, 6L, 8L, 0, 13L, 3L, 4L,
            14L, 7L, 5L, 11L
        }, {
            4L, 2L, 1L, 11L, 10L, 13L, 7L, 8L, 15L, 9L,
            12L, 5L, 6L, 3L, 0, 14L, 11L, 8L, 12L, 7L,
            1L, 14L, 2L, 13L, 6L, 15L, 0, 9L, 10L, 4L,
            5L, 3L, 12L, 1L, 10L, 15L, 9L, 2L, 6L, 8L,
            0, 13L, 3L, 4L, 14L, 7L, 5L, 11L, 10L, 15L,
            4L, 2L, 7L, 12L, 9L, 5L, 6L, 1L, 13L, 14L,
            0, 11L, 3L, 8L
        }, {
            11L, 8L, 12L, 7L, 1L, 14L, 2L, 13L, 6L, 15L,
            0, 9L, 10L, 4L, 5L, 3L, 12L, 1L, 10L, 15L,
            9L, 2L, 6L, 8L, 0, 13L, 3L, 4L, 14L, 7L,
            5L, 11L, 10L, 15L, 4L, 2L, 7L, 12L, 9L, 5L,
            6L, 1L, 13L, 14L, 0, 11L, 3L, 8L, 9L, 14L,
            15L, 5L, 2L, 8L, 12L, 3L, 7L, 0, 4L, 10L,
            1L, 13L, 11L, 6L
        }
    };
    static long S6[][] = {
        {
            12L, 1L, 10L, 15L, 9L, 2L, 6L, 8L, 0, 13L,
            3L, 4L, 14L, 7L, 5L, 11L, 10L, 15L, 4L, 2L,
            7L, 12L, 9L, 5L, 6L, 1L, 13L, 14L, 0, 11L,
            3L, 8L, 9L, 14L, 15L, 5L, 2L, 8L, 12L, 3L,
            7L, 0, 4L, 10L, 1L, 13L, 11L, 6L, 4L, 3L,
            2L, 12L, 9L, 5L, 15L, 10L, 11L, 14L, 1L, 7L,
            6L, 0, 8L, 13L
        }, {
            10L, 15L, 4L, 2L, 7L, 12L, 9L, 5L, 6L, 1L,
            13L, 14L, 0, 11L, 3L, 8L, 9L, 14L, 15L, 5L,
            2L, 8L, 12L, 3L, 7L, 0, 4L, 10L, 1L, 13L,
            11L, 6L, 4L, 3L, 2L, 12L, 9L, 5L, 15L, 10L,
            11L, 14L, 1L, 7L, 6L, 0, 8L, 13L, 4L, 11L,
            2L, 14L, 15L, 0, 8L, 13L, 3L, 12L, 9L, 7L,
            5L, 10L, 6L, 1L
        }, {
            9L, 14L, 15L, 5L, 2L, 8L, 12L, 3L, 7L, 0,
            4L, 10L, 1L, 13L, 11L, 6L, 4L, 3L, 2L, 12L,
            9L, 5L, 15L, 10L, 11L, 14L, 1L, 7L, 6L, 0,
            8L, 13L, 4L, 11L, 2L, 14L, 15L, 0, 8L, 13L,
            3L, 12L, 9L, 7L, 5L, 10L, 6L, 1L, 13L, 0,
            11L, 7L, 4L, 9L, 1L, 10L, 14L, 3L, 5L, 12L,
            2L, 15L, 8L, 6L
        }, {
            4L, 3L, 2L, 12L, 9L, 5L, 15L, 10L, 11L, 14L,
            1L, 7L, 6L, 0, 8L, 13L, 4L, 11L, 2L, 14L,
            15L, 0, 8L, 13L, 3L, 12L, 9L, 7L, 5L, 10L,
            6L, 1L, 13L, 0, 11L, 7L, 4L, 9L, 1L, 10L,
            14L, 3L, 5L, 12L, 2L, 15L, 8L, 6L, 1L, 4L,
            11L, 13L, 12L, 3L, 7L, 14L, 10L, 15L, 6L, 8L,
            0, 5L, 9L, 2L
        }
    };
    static long S7[][] = {
        {
            4L, 11L, 2L, 14L, 15L, 0, 8L, 13L, 3L, 12L,
            9L, 7L, 5L, 10L, 6L, 1L, 13L, 0, 11L, 7L,
            4L, 9L, 1L, 10L, 14L, 3L, 5L, 12L, 2L, 15L,
            8L, 6L, 1L, 4L, 11L, 13L, 12L, 3L, 7L, 14L,
            10L, 15L, 6L, 8L, 0, 5L, 9L, 2L, 6L, 11L,
            13L, 8L, 1L, 4L, 10L, 7L, 9L, 5L, 0, 15L,
            14L, 2L, 3L, 12L
        }, {
            13L, 0, 11L, 7L, 4L, 9L, 1L, 10L, 14L, 3L,
            5L, 12L, 2L, 15L, 8L, 6L, 1L, 4L, 11L, 13L,
            12L, 3L, 7L, 14L, 10L, 15L, 6L, 8L, 0, 5L,
            9L, 2L, 6L, 11L, 13L, 8L, 1L, 4L, 10L, 7L,
            9L, 5L, 0, 15L, 14L, 2L, 3L, 12L, 13L, 2L,
            8L, 4L, 6L, 15L, 11L, 1L, 10L, 9L, 3L, 14L,
            5L, 0, 12L, 7L
        }, {
            1L, 4L, 11L, 13L, 12L, 3L, 7L, 14L, 10L, 15L,
            6L, 8L, 0, 5L, 9L, 2L, 6L, 11L, 13L, 8L,
            1L, 4L, 10L, 7L, 9L, 5L, 0, 15L, 14L, 2L,
            3L, 12L, 13L, 2L, 8L, 4L, 6L, 15L, 11L, 1L,
            10L, 9L, 3L, 14L, 5L, 0, 12L, 7L, 1L, 15L,
            13L, 8L, 10L, 3L, 7L, 4L, 12L, 5L, 6L, 11L,
            0, 14L, 9L, 2L
        }, {
            6L, 11L, 13L, 8L, 1L, 4L, 10L, 7L, 9L, 5L,
            0, 15L, 14L, 2L, 3L, 12L, 13L, 2L, 8L, 4L,
            6L, 15L, 11L, 1L, 10L, 9L, 3L, 14L, 5L, 0,
            12L, 7L, 1L, 15L, 13L, 8L, 10L, 3L, 7L, 4L,
            12L, 5L, 6L, 11L, 0, 14L, 9L, 2L, 7L, 11L,
            4L, 1L, 9L, 12L, 14L, 2L, 0, 6L, 10L, 13L,
            15L, 3L, 5L, 8L
        }
    };
    static long S8[][] = {
        {
            13L, 2L, 8L, 4L, 6L, 15L, 11L, 1L, 10L, 9L,
            3L, 14L, 5L, 0, 12L, 7L, 1L, 15L, 13L, 8L,
            10L, 3L, 7L, 4L, 12L, 5L, 6L, 11L, 0, 14L,
            9L, 2L, 7L, 11L, 4L, 1L, 9L, 12L, 14L, 2L,
            0, 6L, 10L, 13L, 15L, 3L, 5L, 8L, 2L, 1L,
            14L, 7L, 4L, 10L, 8L, 13L, 15L, 12L, 9L, 0,
            3L, 5L, 6L, 11L
        }, {
            1L, 15L, 13L, 8L, 10L, 3L, 7L, 4L, 12L, 5L,
            6L, 11L, 0, 14L, 9L, 2L, 7L, 11L, 4L, 1L,
            9L, 12L, 14L, 2L, 0, 6L, 10L, 13L, 15L, 3L,
            5L, 8L, 2L, 1L, 14L, 7L, 4L, 10L, 8L, 13L,
            15L, 12L, 9L, 0, 3L, 5L, 6L, 11L, 14L, 4L,
            13L, 1L, 2L, 15L, 11L, 8L, 3L, 10L, 6L, 12L,
            5L, 9L, 0, 7L
        }, {
            7L, 11L, 4L, 1L, 9L, 12L, 14L, 2L, 0, 6L,
            10L, 13L, 15L, 3L, 5L, 8L, 2L, 1L, 14L, 7L,
            4L, 10L, 8L, 13L, 15L, 12L, 9L, 0, 3L, 5L,
            6L, 11L, 14L, 4L, 13L, 1L, 2L, 15L, 11L, 8L,
            3L, 10L, 6L, 12L, 5L, 9L, 0, 7L, 0, 15L,
            7L, 4L, 14L, 2L, 13L, 1L, 10L, 6L, 12L, 11L,
            9L, 5L, 3L, 8L
        }, {
            2L, 1L, 14L, 7L, 4L, 10L, 8L, 13L, 15L, 12L,
            9L, 0, 3L, 5L, 6L, 11L, 14L, 4L, 13L, 1L,
            2L, 15L, 11L, 8L, 3L, 10L, 6L, 12L, 5L, 9L,
            0, 7L, 0, 15L, 7L, 4L, 14L, 2L, 13L, 1L,
            10L, 6L, 12L, 11L, 9L, 5L, 3L, 8L, 4L, 1L,
            14L, 8L, 13L, 6L, 2L, 11L, 15L, 12L, 9L, 7L,
            3L, 10L, 5L, 0
        }
    };
    final long I8bits = 0x210000L;
    final long I7bits = 0x8400000L;
    final long I5bits = 132L;
    final long I4bits = 8448L;
    final long I3bits = 0x84000L;
    final long I2bits = 0x2100000L;
    final long I1bits = 0x84000000L;
    final long Jbits = 15L;

    public Descrypt()
    {
        NN = 8;
    }

    public String descrypt(String str, String Key)
    {
        long ink[] = new long[2];
        long outk[][] = new long[16][2];
        byte key0[] = new byte[7];
        byte cyt_str[] = new byte[8];
        int i = 0;
        byte str1[] = str.getBytes();
        byte string[] = new byte[8];
        if(str.length() >= 8)
        {
            for(i = 0; i < 8; i++)
            {
                string[i] = str1[i];
            }

        } else
        {
            for(i = 0; i < str.length(); i++)
            {
                string[i] = str1[i];
            }

            for(i = str.length(); i < 8; i++)
            {
                string[i] = 120;
            }

        }
        byte key[] = Key.getBytes();
        if(key.length >= 7)
        {
            for(i = 0; i < 7; i++)
            {
                key0[i] = key[i];
            }

        } else
        {
            for(i = 0; i < key.length; i++)
            {
                key0[i] = key[i];
            }

            for(i = key.length; i < 7; i++)
            {
                key0[i] = 120;
            }

        }
        for(i = 3; i >= 0; i--)
        {
            ink[0] = ink[0] * 256L + (long)key0[i];
        }

        for(i = 6; i > 3; i--)
        {
            ink[1] = ink[1] * 256L + (long)key0[i];
        }

        outk = KS(ink, outk);
        cyt_str = encrypttxt(string, cyt_str, outk);
        for(i = 0; i < 8; i++) { }
        for(i = 0; i < 8; i++)
        {
            long ch;
            for(ch = cyt_str[i]; ch < 32L || ch > 126L; ch = (ch + 95L) % 256L) { }
            string[i] = (byte)(int)ch;
        }

        str = new String(string);
        return str;
    }

    long[] E(long in, long out[])
    {
        long out0 = 0L;
        long out1 = 0L;
        out0 |= (in & 1L) << 31;
        out0 |= (in & 0x80000000L) >> 1;
        out0 |= (in & 0x40000000L) >> 1;
        out0 |= (in & 0x20000000L) >> 1;
        out0 |= (in & 0x10000000L) >> 1;
        out0 |= (in & 0x8000000L) >> 1;
        out0 |= (in & 0x10000000L) >> 3;
        out0 |= (in & 0x8000000L) >> 3;
        out0 |= (in & 0x4000000L) >> 3;
        out0 |= (in & 0x2000000L) >> 3;
        out0 |= (in & 0x1000000L) >> 3;
        out0 |= (in & 0x800000L) >> 3;
        out0 |= (in & 0x1000000L) >> 5;
        out0 |= (in & 0x800000L) >> 5;
        out0 |= (in & 0x400000L) >> 5;
        out0 |= (in & 0x200000L) >> 5;
        out0 |= (in & 0x100000L) >> 5;
        out0 |= (in & 0x80000L) >> 5;
        out0 |= (in & 0x100000L) >> 7;
        out0 |= (in & 0x80000L) >> 7;
        out0 |= (in & 0x40000L) >> 7;
        out0 |= (in & 0x20000L) >> 7;
        out0 |= (in & 0x10000L) >> 7;
        out0 |= (in & 32768L) >> 7;
        out0 |= (in & 0x10000L) >> 9;
        out0 |= (in & 32768L) >> 9;
        out0 |= (in & 16384L) >> 9;
        out0 |= (in & 8192L) >> 9;
        out0 |= (in & 4096L) >> 9;
        out0 |= (in & 2048L) >> 9;
        out0 |= (in & 4096L) >> 11;
        out0 |= (in & 2048L) >> 11;
        out1 |= (in & 1024L) << 21;
        out1 |= (in & 512L) << 21;
        out1 |= (in & 256L) << 21;
        out1 |= (in & 128L) << 21;
        out1 |= (in & 256L) << 19;
        out1 |= (in & 128L) << 19;
        out1 |= (in & 64L) << 19;
        out1 |= (in & 32L) << 19;
        out1 |= (in & 16L) << 19;
        out1 |= (in & 8L) << 19;
        out1 |= (in & 16L) << 17;
        out1 |= (in & 8L) << 17;
        out1 |= (in & 4L) << 17;
        out1 |= (in & 2L) << 17;
        out1 |= (in & 1L) << 17;
        out1 |= (in & 0x80000000L) >> 15;
        out0 = wszh(out0);
        out1 = wszh(out1);
        out[0] = out0;
        out[1] = out1;
        return out;
    }

    long S(long in[])
    {
        long out = 0L;
        out |= S1[(int)((in[0] & 0x84000000L) + 0x3c000000L >> 30)][(int)(in[0] >> 27 & 15L)] << 28;
        out |= S2[(int)((in[0] & 0x2100000L) + 0xf00000L >> 24)][(int)(in[0] >> 21 & 15L)] << 24;
        out |= S3[(int)((in[0] & 0x84000L) + 0x3c000L >> 18)][(int)(in[0] >> 15 & 15L)] << 20;
        out |= S4[(int)((in[0] & 8448L) + 3840L >> 12)][(int)(in[0] >> 9 & 15L)] << 16;
        out |= S5[(int)((in[0] & 132L) + 60L >> 6)][(int)(in[0] >> 3 & 15L)] << 12;
        long temp = ((in[0] & 3L) << 4) + (in[1] >> 28);
        out |= S6[(int)((temp & 33L) + 15L >> 4)][(int)(temp >> 1)] << 8;
        out |= S7[(int)((in[1] & 0x8400000L) + 0x3c00000L >> 26)][(int)(in[1] >> 23 & 15L)] << 4;
        out |= S8[(int)((in[1] & 0x210000L) + 0xf0000L >> 20)][(int)(in[1] >> 17 & 15L)];
        return wszh(out);
    }

    long P(long in)
    {
        long out = 0L;
        out |= (in & 0x10000L) << 15;
        out |= (in & 0x2000000L) << 5;
        out |= (in & 4096L) << 17;
        out |= (in & 2048L) << 17;
        out |= (in & 8L) << 24;
        out |= (in & 0x100000L) << 6;
        out |= (in & 16L) << 21;
        out |= (in & 32768L) << 9;
        out |= (in & 0x80000000L) >> 8;
        out |= (in & 0x20000L) << 5;
        out |= (in & 512L) << 12;
        out |= (in & 64L) << 14;
        out |= (in & 0x8000000L) >> 8;
        out |= (in & 16384L) << 4;
        out |= (in & 2L) << 16;
        out |= (in & 0x400000L) >> 6;
        out |= (in & 0x40000000L) >> 15;
        out |= (in & 0x1000000L) >> 10;
        out |= (in & 256L) << 5;
        out |= (in & 0x40000L) >> 6;
        out |= (in & 1L) << 11;
        out |= (in & 32L) << 5;
        out |= (in & 0x20000000L) >> 20;
        out |= (in & 0x800000L) >> 15;
        out |= (in & 8192L) >> 6;
        out |= (in & 0x80000L) >> 13;
        out |= (in & 4L) << 3;
        out |= (in & 0x4000000L) >> 22;
        out |= (in & 1024L) >> 7;
        out |= (in & 0x200000L) >> 19;
        out |= (in & 0x10000000L) >> 27;
        out |= (in & 128L) >> 7;
        return wszh(out);
    }

    long[] IP(long in[])
    {
        long in0 = in[0];
        long in1 = in[1];
        long out0 = 0L;
        long out1 = 0L;
        out0 |= (in1 & 64L) << 25;
        out0 |= (in1 & 16384L) << 16;
        out0 |= (in1 & 0x400000L) << 7;
        out0 |= (in1 & 0x40000000L) >> 2;
        out0 |= (in0 & 64L) << 21;
        out0 |= (in0 & 16384L) << 12;
        out0 |= (in0 & 0x400000L) << 3;
        out0 |= (in0 & 0x40000000L) >> 6;
        out0 |= (in1 & 16L) << 19;
        out0 |= (in1 & 4096L) << 10;
        out0 |= (in1 & 0x100000L) << 1;
        out0 |= (in1 & 0x10000000L) >> 8;
        out0 |= (in0 & 16L) << 15;
        out0 |= (in0 & 4096L) << 6;
        out0 |= (in0 & 0x100000L) >> 3;
        out0 |= (in0 & 0x10000000L) >> 12;
        out0 |= (in1 & 4L) << 13;
        out0 |= (in1 & 1024L) << 4;
        out0 |= (in1 & 0x40000L) >> 5;
        out0 |= (in1 & 0x4000000L) >> 14;
        out0 |= (in0 & 4L) << 9;
        out0 |= in0 & 1024L;
        out0 |= (in0 & 0x40000L) >> 9;
        out0 |= (in0 & 0x4000000L) >> 18;
        out0 |= (in1 & 1L) << 7;
        out0 |= (in1 & 256L) >> 2;
        out0 |= (in1 & 0x10000L) >> 11;
        out0 |= (in1 & 0x1000000L) >> 20;
        out0 |= (in0 & 1L) << 3;
        out0 |= (in0 & 256L) >> 6;
        out0 |= (in0 & 0x10000L) >> 15;
        out0 |= (in0 & 0x1000000L) >> 24;
        out1 |= (in1 & 128L) << 24;
        out1 |= (in1 & 32768L) << 15;
        out1 |= (in1 & 0x800000L) << 6;
        out1 |= (in1 & 0x80000000L) >> 3;
        out1 |= (in0 & 128L) << 20;
        out1 |= (in0 & 32768L) << 11;
        out1 |= (in0 & 0x800000L) << 2;
        out1 |= (in0 & 0x80000000L) >> 7;
        out1 |= (in1 & 32L) << 18;
        out1 |= (in1 & 8192L) << 9;
        out1 |= in1 & 0x200000L;
        out1 |= (in1 & 0x20000000L) >> 9;
        out1 |= (in0 & 32L) << 14;
        out1 |= (in0 & 8192L) << 5;
        out1 |= (in0 & 0x200000L) >> 4;
        out1 |= (in0 & 0x20000000L) >> 13;
        out1 |= (in1 & 8L) << 12;
        out1 |= (in1 & 2048L) << 3;
        out1 |= (in1 & 0x80000L) >> 6;
        out1 |= (in1 & 0x8000000L) >> 15;
        out1 |= (in0 & 8L) << 8;
        out1 |= (in0 & 2048L) >> 1;
        out1 |= (in0 & 0x80000L) >> 10;
        out1 |= (in0 & 0x8000000L) >> 19;
        out1 |= (in1 & 2L) << 6;
        out1 |= (in1 & 512L) >> 3;
        out1 |= (in1 & 0x20000L) >> 12;
        out1 |= (in1 & 0x2000000L) >> 21;
        out1 |= (in0 & 2L) << 2;
        out1 |= (in0 & 512L) >> 7;
        out1 |= (in0 & 0x20000L) >> 16;
        out1 |= (in0 & 0x2000000L) >> 25;
        long back[] = new long[2];
        out0 = wszh(out0);
        out1 = wszh(out1);
        back[0] = out0;
        back[1] = out1;
        return back;
    }

    long[] IP_1(long l, long r, long out[])
    {
        long out0 = 0L;
        long out1 = 0L;
        out0 |= (l & 0x1000000L) << 7;
        out0 |= (r & 0x1000000L) << 6;
        out0 |= (l & 0x10000L) << 13;
        out0 |= (r & 0x10000L) << 12;
        out0 |= (l & 256L) << 19;
        out0 |= (r & 256L) << 18;
        out0 |= (l & 1L) << 25;
        out0 |= (r & 1L) << 24;
        out0 |= (l & 0x2000000L) >> 2;
        out0 |= (r & 0x2000000L) >> 3;
        out0 |= (l & 0x20000L) << 4;
        out0 |= (r & 0x20000L) << 3;
        out0 |= (l & 512L) << 10;
        out0 |= (r & 512L) << 9;
        out0 |= (l & 2L) << 16;
        out0 |= (r & 2L) << 15;
        out0 |= (l & 0x4000000L) >> 11;
        out0 |= (r & 0x4000000L) >> 12;
        out0 |= (l & 0x40000L) >> 5;
        out0 |= (r & 0x40000L) >> 6;
        out0 |= (l & 1024L) << 1;
        out0 |= r & 1024L;
        out0 |= (l & 4L) << 7;
        out0 |= (r & 4L) << 6;
        out0 |= (l & 0x8000000L) >> 20;
        out0 |= (r & 0x8000000L) >> 21;
        out0 |= (l & 0x80000L) >> 14;
        out0 |= (r & 0x80000L) >> 15;
        out0 |= (l & 2048L) >> 8;
        out0 |= (r & 2048L) >> 9;
        out0 |= (l & 8L) >> 2;
        out0 |= (r & 8L) >> 3;
        out1 |= (l & 0x10000000L) << 3;
        out1 |= (r & 0x10000000L) << 2;
        out1 |= (l & 0x100000L) << 9;
        out1 |= (r & 0x100000L) << 8;
        out1 |= (l & 4096L) << 15;
        out1 |= (r & 4096L) << 14;
        out1 |= (l & 16L) << 21;
        out1 |= (r & 16L) << 20;
        out1 |= (l & 0x20000000L) >> 6;
        out1 |= (r & 0x20000000L) >> 7;
        out1 |= l & 0x200000L;
        out1 |= (r & 0x200000L) >> 1;
        out1 |= (l & 8192L) << 6;
        out1 |= (r & 8192L) << 5;
        out1 |= (l & 32L) << 12;
        out1 |= (r & 32L) << 11;
        out1 |= (l & 0x40000000L) >> 15;
        out1 |= (r & 0x40000000L) >> 16;
        out1 |= (l & 0x400000L) >> 9;
        out1 |= (r & 0x400000L) >> 10;
        out1 |= (l & 16384L) >> 3;
        out1 |= (r & 16384L) >> 4;
        out1 |= (l & 64L) << 3;
        out1 |= (r & 64L) << 2;
        out1 |= (l & 0x80000000L) >> 24;
        out1 |= (r & 0x80000000L) >> 25;
        out1 |= (l & 0x800000L) >> 18;
        out1 |= (r & 0x800000L) >> 19;
        out1 |= (l & 32768L) >> 12;
        out1 |= (r & 32768L) >> 13;
        out1 |= (l & 128L) >> 6;
        out1 |= (r & 128L) >> 7;
        out0 = wszh(out0);
        out1 = wszh(out1);
        out[0] = out0;
        out[1] = out1;
        return out;
    }

    long[] PC_1(long in[])
    {
        long in0 = in[0];
        long in1 = in[1];
        long out1;
        long out0 = out1 = 0L;
        out0 |= (in1 & 128L) << 24;
        out0 |= (in1 & 32768L) << 15;
        out0 |= (in1 & 0x800000L) << 6;
        out0 |= (in1 & 0x80000000L) >> 3;
        out0 |= (in0 & 128L) << 20;
        out0 |= (in0 & 32768L) << 11;
        out0 |= (in0 & 0x800000L) << 2;
        out0 |= (in0 & 0x80000000L) >> 7;
        out0 |= (in1 & 64L) << 17;
        out0 |= (in1 & 16384L) << 8;
        out0 |= (in1 & 0x400000L) >> 1;
        out0 |= (in1 & 0x40000000L) >> 10;
        out0 |= (in0 & 64L) << 13;
        out0 |= (in0 & 16384L) << 4;
        out0 |= (in0 & 0x400000L) >> 5;
        out0 |= (in0 & 0x40000000L) >> 14;
        out0 |= (in1 & 32L) << 10;
        out0 |= (in1 & 8192L) << 1;
        out0 |= (in1 & 0x200000L) >> 8;
        out0 |= (in1 & 0x20000000L) >> 17;
        out0 |= (in0 & 32L) << 6;
        out0 |= (in0 & 8192L) >> 3;
        out0 |= (in0 & 0x200000L) >> 12;
        out0 |= (in0 & 0x20000000L) >> 21;
        out0 |= (in1 & 16L) << 3;
        out0 |= (in1 & 4096L) >> 6;
        out0 |= (in1 & 0x100000L) >> 15;
        out0 |= (in1 & 0x10000000L) >> 24;
        out0 |= (in0 & 2L) << 2;
        out0 |= (in0 & 512L) >> 7;
        out0 |= (in0 & 0x20000L) >> 16;
        out0 |= (in0 & 0x2000000L) >> 25;
        out1 |= (in0 & 2L) << 30;
        out1 |= (in0 & 512L) << 21;
        out1 |= (in0 & 0x20000L) << 12;
        out1 |= (in0 & 0x2000000L) << 3;
        out1 |= (in1 & 4L) << 25;
        out1 |= (in1 & 1024L) << 16;
        out1 |= (in1 & 0x40000L) << 7;
        out1 |= (in1 & 0x4000000L) >> 2;
        out1 |= (in0 & 4L) << 21;
        out1 |= (in0 & 1024L) << 12;
        out1 |= (in0 & 0x40000L) << 3;
        out1 |= (in0 & 0x4000000L) >> 6;
        out1 |= (in1 & 8L) << 16;
        out1 |= (in1 & 2048L) << 7;
        out1 |= (in1 & 0x80000L) >> 2;
        out1 |= (in1 & 0x8000000L) >> 11;
        out1 |= (in0 & 8L) << 12;
        out1 |= (in0 & 2048L) << 3;
        out1 |= (in0 & 0x80000L) >> 6;
        out1 |= (in0 & 0x8000000L) >> 15;
        out1 |= (in0 & 16L) << 7;
        out1 |= (in0 & 4096L) >> 2;
        out1 |= (in0 & 0x100000L) >> 11;
        out1 |= (in0 & 0x10000000L) >> 20;
        long back[] = new long[2];
        back[0] = out0 >> 4;
        back[1] = (out0 & 15L) << 24 | out1 >> 8;
        back[0] = wszh(back[0]);
        back[1] = wszh(back[1]);
        return back;
    }

    long[] PC_2(long c, long d, long out[])
    {
        long out1;
        long out0 = out1 = 0L;
        long in0 = c << 4 | d >> 24 & 15L;
        long in1 = d << 8 & -1L;
        String str = Long.toBinaryString(in1);
        int i = str.length();
        if(i > 32)
        {
            str = str.substring(i - 32);
        }
        int j = str.indexOf("1");
        str = str.substring(j);
        in1 = Long.valueOf(str, 2).longValue();
        out0 |= (in0 & 0x40000L) << 13;
        out0 |= (in0 & 32768L) << 15;
        out0 |= (in0 & 0x200000L) << 8;
        out0 |= (in0 & 256L) << 20;
        out0 |= (in0 & 0x80000000L) >> 4;
        out0 |= (in0 & 0x8000000L) >> 1;
        out0 |= (in0 & 0x20000000L) >> 4;
        out0 |= (in0 & 16L) << 20;
        out0 |= (in0 & 0x20000L) << 6;
        out0 |= (in0 & 0x4000000L) >> 4;
        out0 |= (in0 & 2048L) << 10;
        out0 |= (in0 & 0x400000L) >> 2;
        out0 |= (in0 & 512L) << 10;
        out0 |= (in0 & 8192L) << 5;
        out0 |= (in0 & 0x100000L) >> 3;
        out0 |= (in0 & 0x10000000L) >> 12;
        out0 |= (in0 & 64L) << 9;
        out0 |= (in0 & 0x1000000L) >> 10;
        out0 |= (in0 & 0x10000L) >> 3;
        out0 |= (in0 & 0x2000000L) >> 13;
        out0 |= (in0 & 32L) << 6;
        out0 |= (in0 & 4096L) >> 2;
        out0 |= (in0 & 0x80000L) >> 10;
        out0 |= (in0 & 0x40000000L) >> 22;
        out0 |= (in1 & 0x800000L) >> 16;
        out0 |= (in1 & 4096L) >> 6;
        out0 |= (in0 & 2L) << 4;
        out0 |= (in1 & 0x8000000L) >> 23;
        out0 |= (in1 & 0x20000L) >> 14;
        out0 |= (in1 & 512L) >> 7;
        out0 |= (in0 & 4L) >> 1;
        out0 |= (in1 & 0x1000000L) >> 24;
        out1 |= (in1 & 8192L) << 18;
        out1 |= (in1 & 0x80000L) << 11;
        out1 |= (in1 & 0x80000000L) >> 2;
        out1 |= (in1 & 0x10000L) << 12;
        out1 |= (in1 & 0x100000L) << 7;
        out1 |= (in1 & 32768L) << 11;
        out1 |= in1 & 0x2000000L;
        out1 |= (in1 & 256L) << 16;
        out1 |= (in1 & 0x40000000L) >> 7;
        out1 |= (in1 & 2048L) << 11;
        out1 |= (in1 & 0x40000L) << 3;
        out1 |= (in1 & 0x400000L) >> 2;
        out1 |= (in1 & 16384L) << 5;
        out1 |= (in1 & 0x10000000L) >> 10;
        out1 |= (in0 & 8L) << 14;
        out1 |= (in0 & 1L) << 16;
        out0 = wszh(out0);
        out1 = wszh(out1);
        out[0] = out0;
        out[1] = out1;
        return out;
    }

    long[][] KS(long ink[], long outk[][])
    {
        long back[] = new long[2];
        back = PC_1(ink);
        long c = back[0];
        long d = back[1];
        c = (c << 1 | c >> 27 & 1L) & 0xfffffffL;
        d = (d << 1 | d >> 27 & 1L) & 0xfffffffL;
        outk[0] = PC_2(c, d, outk[0]);
        c = (c << 1 | c >> 27 & 1L) & 0xfffffffL;
        d = (d << 1 | d >> 27 & 1L) & 0xfffffffL;
        outk[1] = PC_2(c, d, outk[1]);
        c = (c << 2 | c >> 26 & 1L) & 0xfffffffL;
        d = (d << 2 | d >> 26 & 1L) & 0xfffffffL;
        outk[2] = PC_2(c, d, outk[2]);
        c = (c << 2 | c >> 26 & 1L) & 0xfffffffL;
        d = (d << 2 | d >> 26 & 1L) & 0xfffffffL;
        outk[3] = PC_2(c, d, outk[3]);
        c = (c << 2 | c >> 26 & 1L) & 0xfffffffL;
        d = (d << 2 | d >> 26 & 1L) & 0xfffffffL;
        outk[4] = PC_2(c, d, outk[4]);
        c = (c << 2 | c >> 26 & 1L) & 0xfffffffL;
        d = (d << 2 | d >> 26 & 1L) & 0xfffffffL;
        outk[5] = PC_2(c, d, outk[5]);
        c = (c << 2 | c >> 26 & 1L) & 0xfffffffL;
        d = (d << 2 | d >> 26 & 1L) & 0xfffffffL;
        outk[6] = PC_2(c, d, outk[6]);
        c = (c << 2 | c >> 26 & 1L) & 0xfffffffL;
        d = (d << 2 | d >> 26 & 1L) & 0xfffffffL;
        outk[7] = PC_2(c, d, outk[7]);
        c = (c << 1 | c >> 27 & 1L) & 0xfffffffL;
        d = (d << 1 | d >> 27 & 1L) & 0xfffffffL;
        outk[8] = PC_2(c, d, outk[8]);
        c = (c << 2 | c >> 26 & 1L) & 0xfffffffL;
        d = (d << 2 | d >> 26 & 1L) & 0xfffffffL;
        outk[9] = PC_2(c, d, outk[9]);
        c = (c << 2 | c >> 26 & 1L) & 0xfffffffL;
        d = (d << 2 | d >> 26 & 1L) & 0xfffffffL;
        outk[10] = PC_2(c, d, outk[10]);
        c = (c << 2 | c >> 26 & 1L) & 0xfffffffL;
        d = (d << 2 | d >> 26 & 1L) & 0xfffffffL;
        outk[11] = PC_2(c, d, outk[11]);
        c = (c << 2 | c >> 26 & 1L) & 0xfffffffL;
        d = (d << 2 | d >> 26 & 1L) & 0xfffffffL;
        outk[12] = PC_2(c, d, outk[12]);
        c = (c << 2 | c >> 26 & 1L) & 0xfffffffL;
        d = (d << 2 | d >> 26 & 1L) & 0xfffffffL;
        outk[13] = PC_2(c, d, outk[13]);
        c = (c << 2 | c >> 26 & 1L) & 0xfffffffL;
        d = (d << 2 | d >> 26 & 1L) & 0xfffffffL;
        outk[14] = PC_2(c, d, outk[14]);
        c = (c << 1 | c >> 27 & 1L) & 0xfffffffL;
        d = (d << 1 | d >> 27 & 1L) & 0xfffffffL;
        outk[15] = PC_2(c, d, outk[15]);
        return outk;
    }

    byte[] encrypttxt(byte intxt[], byte outtxt[], long k[][])
    {
        long in[] = new long[2];
        long out[] = new long[2];
        long eout[] = new long[2];
        in[0] = (long)intxt[0] | (long)intxt[1] << 8 | (long)intxt[2] << 16 | (long)intxt[3] << 24;
        in[1] = (long)intxt[4] | (long)intxt[5] << 8 | (long)intxt[6] << 16 | (long)intxt[7] << 24;
        long back[] = new long[2];
        back = IP(in);
        long l = wszh(back[0]);
        long r = wszh(back[1]);
        for(int i = 0; i < NN; i++)
        {
            eout = E(r, eout);
            eout[0] ^= k[i][0];
            eout[1] ^= k[i][1];
            long sout = S(eout);
            long pout = P(sout);
            pout ^= l;
            l = r;
            r = pout;
        }

        out = IP_1(l, r, out);
        outtxt[0] = (byte)(int)(out[0] & 255L);
        outtxt[1] = (byte)(int)(out[0] >> 8 & 255L);
        outtxt[2] = (byte)(int)(out[0] >> 16 & 255L);
        outtxt[3] = (byte)(int)(out[0] >> 24 & 255L);
        outtxt[4] = (byte)(int)(out[1] & 255L);
        outtxt[5] = (byte)(int)(out[1] >> 8 & 255L);
        outtxt[6] = (byte)(int)(out[1] >> 16 & 255L);
        outtxt[7] = (byte)(int)(out[1] >> 24 & 255L);
        return outtxt;
    }

    byte[] decrypttxt(byte intxt[], byte outtxt[], long k[][])
    {
        long in[] = new long[2];
        long out[] = new long[2];
        long eout[] = new long[2];
        in[0] = (long)intxt[0] | (long)intxt[1] << 8 | (long)intxt[2] << 16 | (long)intxt[3] << 24;
        in[1] = (long)intxt[4] | (long)intxt[5] << 8 | (long)intxt[6] << 16 | (long)intxt[7] << 24;
        long back[] = new long[2];
        back = IP(in);
        long l = back[0];
        long r = back[1];
        for(int i = NN - 1; i >= 0; i--)
        {
            eout = E(r, eout);
            eout[0] ^= k[i][0];
            eout[1] ^= k[i][1];
            long sout = S(eout);
            long pout = P(sout);
            pout ^= l;
            l = r;
            r = pout;
        }

        out = IP_1(l, r, out);
        outtxt[0] = (byte)(int)(out[0] & 255L);
        outtxt[1] = (byte)(int)(out[0] >> 8 & 255L);
        outtxt[2] = (byte)(int)(out[0] >> 16 & 255L);
        outtxt[3] = (byte)(int)(out[0] >> 24 & 255L);
        outtxt[4] = (byte)(int)(out[1] & 255L);
        outtxt[5] = (byte)(int)(out[1] >> 8 & 255L);
        outtxt[6] = (byte)(int)(out[1] >> 16 & 255L);
        outtxt[7] = (byte)(int)(out[1] >> 24 & 255L);
        return outtxt;
    }

    void encrpt(long in[], long out[], long k[][])
    {
        long eout[] = new long[2];
        long back[] = new long[2];
        back = IP(in);
        long l = back[0];
        long r = back[1];
        for(int i = 0; i < NN; i++)
        {
            eout = E(r, eout);
            eout[0] ^= k[i][0];
            eout[1] ^= k[i][1];
            long sout = S(eout);
            long pout = P(sout);
            pout ^= l;
            l = r;
            r = pout;
        }

        IP_1(l, r, out);
    }

    void decrpt(long in[], long out[], long k[][])
    {
        long eout[] = new long[2];
        long back[] = new long[2];
        back = IP(in);
        long l = back[0];
        long r = back[1];
        for(int i = NN - 1; i >= 0; i--)
        {
            eout = E(r, eout);
            eout[0] ^= k[i][0];
            eout[1] ^= k[i][1];
            long sout = S(eout);
            long pout = P(sout);
            pout ^= l;
            l = r;
            r = pout;
        }

        IP_1(l, r, out);
    }

    long wszh(long l)
    {
        String str = Long.toBinaryString(l);
        int i = str.length();
        if(i > 32)
        {
            str = str.substring(i - 32);
        }
        int j = str.indexOf("1");
        if(j != -1)
        {
            str = str.substring(j);
        }
        l = Long.valueOf(str, 2).longValue();
        return l;
    }

}
