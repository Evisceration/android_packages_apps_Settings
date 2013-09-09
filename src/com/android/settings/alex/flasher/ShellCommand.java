package com.android.settings.alex.flasher;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;

public class ShellCommand {
    private static final String TAG = "ShellCommand.java";

    public SH su;

    public ShellCommand() {
        su = new SH("su");
    }

    public class CommandResult {
        public final String stdout;
        public final String stderr;
        public final Integer exit_value;

        CommandResult(Integer exit_value_in, String stdout_in, String stderr_in) {
            exit_value = exit_value_in;
            stdout = stdout_in;
            stderr = stderr_in;
        }

        CommandResult(Integer exit_value_in) {
            this(exit_value_in, null, null);
        }

        public boolean success() {
            return (exit_value != null) && (exit_value == 0);
        }
    }

    public class SH {
        private String SHELL = "su";

        public SH(String SHELL_in) {
            SHELL = SHELL_in;
        }

        public Process run(String s) {
            Process process = null;
            try {
                process = Runtime.getRuntime().exec(SHELL);
                DataOutputStream toProcess = new DataOutputStream(
                        process.getOutputStream());
                toProcess.writeBytes("exec " + s + "\n");
                toProcess.flush();
            } catch (Exception e) {
                Log.e("OFS",
                        "Exception while trying to run: '" + s + "' "
                                + e.getMessage());
                process = null;
            }
            return process;
        }

        @SuppressWarnings("deprecation")
        private String getStreamLines(InputStream is) {
            String out = null;
            StringBuffer buffer = null;
            DataInputStream dis = new DataInputStream(is);

            try {
                if (dis.available() > 0) {
                    buffer = new StringBuffer(dis.readLine());
                    while (dis.available() > 0) {
                        buffer.append("\n").append(dis.readLine());
                    }
                }
                dis.close();
            } catch (Exception ex) {
                Log.e(ShellCommand.TAG, ex.getMessage());
            }
            if (buffer != null) {
                out = buffer.toString();
            }
            return out;
        }

        public CommandResult runWaitFor(String s) {
            Process process = run(s);
            Integer exit_value = null;
            String stdout = null;
            String stderr = null;
            if (process != null) {
                try {
                    exit_value = process.waitFor();

                    stdout = getStreamLines(process.getInputStream());
                    stderr = getStreamLines(process.getErrorStream());

                } catch (InterruptedException e) {
                    Log.e(ShellCommand.TAG, "runWaitFor " + e.toString());
                } catch (NullPointerException e) {
                    Log.e(ShellCommand.TAG, "runWaitFor " + e.toString());
                }
            }
            return new CommandResult(exit_value, stdout, stderr);
        }
    }
}
