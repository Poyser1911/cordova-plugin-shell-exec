package org.apache.cordova.plugin;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.DataOutputStream;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ShellExec extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray data, final CallbackContext callbackContext) throws JSONException {
        if (action.equals("exec")) {
            final String cmd = data.getString(0);
            //final String rootMode = data.getString(1);
            cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    Process p;
                    StringBuffer output = new StringBuffer();
                   // output.append("Cmd: "+cmd+"\nRoot Mode: "+rootMode+"\n");
                    int exitStatus = -1;
                    try {
                        p = Runtime.getRuntime().exec("su");
                        DataOutputStream outputStream = new DataOutputStream(p.getOutputStream());

                        outputStream.writeBytes(cmd + "\n");
                        outputStream.flush();

                        outputStream.writeBytes("exit\n");
                        outputStream.flush();

                        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                        String line = "";

                        while ((line = reader.readLine()) != null) {
                            output.append(line + "\n");
                        }
                        while ((line = stdError.readLine()) != null) {
                            output.append(line);
                        }
                        exitStatus = p.waitFor();

                    } catch (IOException e) {
                        output.append("IOException: " + e.getMessage() + "\n");
                    } catch (InterruptedException e) {
                        output.append("InterruptedException: " + e.getMessage() + "\n");
                    } catch (Exception e) {
                        output.append("InterruptedException: " + e.getMessage() + "\n");
                    }
                    try {
                        JSONObject json = new JSONObject();
                        json.put("exitStatus", exitStatus);
                        json.put("output", output.toString());
                        callbackContext.success(json);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        callbackContext.success();
                    }
                }
            });
            return true;
        }
        return false;
    }

}