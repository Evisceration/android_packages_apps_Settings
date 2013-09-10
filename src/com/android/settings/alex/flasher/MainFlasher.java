package com.android.settings.alex.flasher;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.settings.R;
import com.android.settings.alex.flasher.ShellCommand;
import com.android.settings.alex.flasher.ShellCommand.CommandResult;

@SuppressLint("DefaultLocale")
public class MainFlasher extends Activity {

    /* Activity Elements */
    Spinner flashType;
    Button bPath, bFlash;
    CheckBox check;

    /* Codes */
    static final int PICK_IMGFILE_REQUEST = 1001;

    /* Common Variables */
    static Context currentInstance;
    String filelocation, selectedFlashtype = "recovery";
    private final String RECOVERY_PATH = "/dev/block/mmcblk0p4";
    ;

	/* Start */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alex_flasher_main);
        MainFlasher.currentInstance = MainFlasher.this.getApplicationContext();
        selectedFlashtype = "recovery";
        setupElements();
    }

    private void setupElements() {
        flashType = (Spinner) findViewById(R.id.spFlashType);
        flashType.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> paramAdapterView,
                                       View paramView, int paramInt, long paramLong) {
                selectedFlashtype = flashType.getSelectedItem().toString()
                        .toLowerCase();
            }

            @Override
            public void onNothingSelected(AdapterView<?> paramAdapterView) {
                selectedFlashtype = "recovery";
            }

        });
        bPath = (Button) findViewById(R.id.bIMGFile);
        bPath.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.setType("file/img");
                Intent intent = Intent.createChooser(chooseFile, "Choose a .img file");
                startActivityForResult(intent,
                        MainFlasher.PICK_IMGFILE_REQUEST);
            }

        });

        check = (CheckBox) findViewById(R.id.cbCheck);
        check.setChecked(false);

        bFlash = (Button) findViewById(R.id.bFlash);
        bFlash.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View paramView) {
                if (check.isChecked()) {
                    if (filelocation != null) {
                        Builder dialog = new AlertDialog.Builder(MainFlasher.this);
                        dialog.setTitle("WARNING!").setMessage(
                                "Please check the entered Values!\n\nFilepath: "
                                        + filelocation + "\nFlash Type: "
                                        + selectedFlashtype
                                        + "\n\nAre these values REALLY right?");
                        dialog.setNegativeButton("No!",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(
                                            DialogInterface paramDialogInterface,
                                            int paramInt) {
                                        paramDialogInterface.dismiss();
                                    }
                                }).setPositiveButton("Yes!",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(
                                            DialogInterface paramDialogInterface,
                                            int paramInt) {
                                        makeToast("Flashing, please wait...");
                                        startFlashing();
                                    }
                                });
                        dialog.show();
                    } else {
                        showSimpleDialog(
                                "ERROR!",
                                "You have to fill in all Information to continue!\n\nPlease be sure, that all entered information is right, otherwise it can have bad results!",
                                "OK");
                    }
                } else {
                    showSimpleDialog(
                            "ERROR!",
                            "You have to check the Checkbox to continue!\n\nPlease be sure, that all entered information is right, otherwise it can have bad results!",
                            "OK");
                }
            }

        });

    }

    private void startFlashing() {
        final ShellCommand sc = new ShellCommand();
        CommandResult cr;
        cr = sc.su.runWaitFor("cp -f " + filelocation
                + " /data/local/tmp/recovery.img");
        cr = sc.su.runWaitFor("chmod 777 /data/local/tmp/recovery.img");
        cr = sc.su.runWaitFor("dd if=/data/local/tmp/recovery.img of="
                + RECOVERY_PATH);
        if (cr.success()) {
            sc.su.runWaitFor("rm -rf /data/local/tmp/recovery.img");
            Builder dialog = new AlertDialog.Builder(MainFlasher.this);
            dialog.setTitle("SUCCESS!").setMessage(
                    "Successfully flashed!\n\nDo you want to reboot into Recovery now?");
            dialog.setNegativeButton("No.",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(
                                DialogInterface paramDialogInterface,
                                int paramInt) {
                            paramDialogInterface.dismiss();
                        }
                    }).setPositiveButton("Yes!",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(
                                DialogInterface paramDialogInterface,
                                int paramInt) {
                            sc.su.run("reboot recovery");
                        }
                    });
            dialog.show();
        } else {
            showSimpleDialog("Error!", "An error occured!\n" + cr.stderr,
                    "Ok...");
        }
        sc.su.runWaitFor("rm -rf /data/local/tmp/recovery.img");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MainFlasher.PICK_IMGFILE_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                filelocation = data.getDataString();
                filelocation = filelocation.replace("file://", "");
                bPath.setText(filelocation);
            }
        }
    }

    private void showSimpleDialog(String title, String msg, String buttonText) {
        Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title)
                .setMessage(msg)
                .setNeutralButton(buttonText,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }

                        }).show();
    }

    private void makeToast(String msg) {
        Toast.makeText(MainFlasher.currentInstance, msg, Toast.LENGTH_SHORT).show();
    }
}
