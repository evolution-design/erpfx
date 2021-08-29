package Forms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Pane;




public class ZalohaFormControler extends Dialog {
    @FXML
    private Pane contentPane;
    @FXML
    private Button buttonOK;
    @FXML
    private Button buttonCancel;

    public ZalohaFormControler() {

        buttonOK.setOnAction((ActionEvent a) -> {
            onOK();
        });

        buttonCancel.setOnAction((ActionEvent a) -> {
            onCancel();
        });


    }

    private void onOK() {
        // add your code here

    }

    private void onCancel() {
        // add your code here if necessary

    }

    public static void main(String[] args) {
        ZalohaFormControler dialog = new ZalohaFormControler();
        System.exit(0);
    }



}
