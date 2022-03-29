package com.neckel.inch5;

import com.pi4j.io.spi.SpiChannel;
import com.pi4j.io.spi.SpiDevice;
import com.pi4j.io.spi.SpiMode;

public class SpiVariables {
    public static final int MAX_WIDTH_SCREEN = 847;
    public static final int MAX_HEIGTHS_SCREEN = 479;

    public static final int MAX_SPI_RETURN_WIDTH_SCREEN = 1925;
    public static final int MAX_SPI_RETURN_HEIGTH_SCREEN = 1925;

    public static final int DISREGARD_WIDTH = 120;
    public static final int DISREGARD_HEIGTH = 120;

    public static final int FREQUENCY = 50000;
    public static final SpiChannel SPI_CHANNEL = SpiChannel.CS0;
    public static final SpiMode SPI_MODE = SpiDevice.DEFAULT_SPI_MODE;

    public static final byte[] BYTES_TO_SEND_REQUEST_SPI_X = new byte[]{(byte) 0xd0, (byte) 0x00, (byte) 0x00};
    public static final byte[] BYTES_TO_SEND_REQUEST_SPI_Y = new byte[]{(byte) 0x90, (byte) 0x00, (byte) 0x00};
}
