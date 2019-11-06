package com.neimeng.workflow.utils;


import java.io.Closeable;
import java.io.IOException;

/**
 * Created by kongkp on 16-8-26.
 */
public final class CloseableUtils {

    private CloseableUtils() {
    }

    public static void close(Closeable... closables) {
        if (closables == null || closables.length == 0) {
            return;
        }

        for (Closeable closable : closables) {
            try {
                if (closable != null) {
                    closable.close();
                }
            } catch (IOException e) {
                System.err.println("Close resource exception:" + e.getStackTrace());
            }
        }
    }

    public static void close(AutoCloseable... closables) {
        if (closables == null || closables.length == 0) {
            return;
        }

        for (AutoCloseable closable : closables) {
            try {
                if (closable != null) {
                    closable.close();
                }
            } catch (Exception e) {
                System.err.println("Close resource exception:" + e.getStackTrace());
            }
        }
    }

}
