package com.neckel.inch5;

import com.neckel.util.ArraysOptions;
import com.neckel.util.MouseOptions;
import com.pi4j.io.spi.SpiDevice;
import com.pi4j.io.spi.SpiFactory;
import com.pi4j.platform.Platform;
import com.pi4j.platform.PlatformManager;

import java.util.ArrayList;
import java.util.List;

public class Touch5Inch {

    private SpiDevice spi = null;
    private byte[] spiData = null;

    private int originalX = 0;
    private int originalY = 0;

    private List<Integer> positionPrevX = new ArrayList();
    private List<Integer> positionPrevY = new ArrayList();

    private int quantityOfClick = 0;

    public void inicialize() {
        try {
            PlatformManager.setPlatform(Platform.ORANGEPI);

            spi = SpiFactory.getInstance(SpiVariables.SPI_CHANNEL, SpiVariables.FREQUENCY, SpiVariables.SPI_MODE);
            System.out.println("Ready...");

            while (true) {
                try {
                    requestingAndReadingSpi();
                    getPositionXandY();
                    processesSpiDataAndClick();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println("error to inicialize spi");
            e.printStackTrace();
        }
    }

    private void simulateMouseClickIfReady() {
        if (positionPrevX.size() == 10) {
            quantityOfClick++;

            double mediaX = (int) positionPrevX.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
            double mediaY = (int) positionPrevY.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);

            mediaX = (SpiVariables.MAX_WIDTH_SCREEN * mediaX) / SpiVariables.MAX_SPI_RETURN_WIDTH_SCREEN;
            mediaY = (SpiVariables.MAX_HEIGTHS_SCREEN * mediaY) / SpiVariables.MAX_SPI_RETURN_HEIGTH_SCREEN;

            MouseOptions.click((int) mediaX, (int) mediaY);
        }
    }

    private void clearPositions() {
        positionPrevX.clear();
        positionPrevY.clear();
    }

    private void requestingAndReadingSpi() {
        try {
            byte[] bufferX = spi.write(SpiVariables.BYTES_TO_SEND_REQUEST_SPI_X);
            byte[] bufferY = spi.write(SpiVariables.BYTES_TO_SEND_REQUEST_SPI_Y);
            spiData = ArraysOptions.concat(bufferX, bufferY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getPositionXandY() {
        originalX = getOriginalPositionX();
        originalY = getOriginalPositionY();
    }

    private void processesSpiDataAndClick() {
        if (isPressing()) {
            if (originalX > 0 && originalY > 0) {
                setMemoryPosition();
                simulateMouseClickIfReady();
            }
        } else {
            clearPositions();
        }
    }

    private int getOriginalPositionX() {
        return (spiData[2] + (spiData[1] << 8) >> 4) - SpiVariables.DISREGARD_WIDTH;
    }

    private int getOriginalPositionY() {
        return (spiData[5] + (spiData[4] << 8) >> 4) - SpiVariables.DISREGARD_HEIGTH;
    }

    public boolean isPressing() {
        return spiData[4] != 127;
    }

    private void setMemoryPosition() {
        positionPrevX.add(originalX);
        positionPrevY.add(originalY);
    }
}
