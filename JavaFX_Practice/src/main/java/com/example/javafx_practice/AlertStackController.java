package com.example.javafx_practice;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class AlertStackController implements Initializable {
    public TextField txtAlertSet1;
    public TextField txtAlertSet2;
    public TextField txtAlertSet3;
    public TextField txtAlertSet4;
    public TextField txtAlertSet5;
    public TextField txtAlertSet6;
    public TextField txtAlertSet7;
    public TextField txtAlertSet8;
    public TextField txtAlertSet9;
    public TextField txtAlertSet10;

    public CheckBox chkAlert1;
    public CheckBox chkAlert2;
    public CheckBox chkAlert3;
    public CheckBox chkAlert4;
    public CheckBox chkAlert5;
    public CheckBox chkAlert6;
    public CheckBox chkAlert7;
    public CheckBox chkAlert8;
    public CheckBox chkAlert9;
    public CheckBox chkAlert10;
    TextField[] alertArr;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alertArr = new TextField[10];
        alertArr[0] =  txtAlertSet1;
        alertArr[1] =  txtAlertSet2;
        alertArr[2] =  txtAlertSet3;
        alertArr[3] =  txtAlertSet4;
        alertArr[4] =  txtAlertSet5;
        alertArr[5] =  txtAlertSet6;
        alertArr[6] =  txtAlertSet7;
        alertArr[7] =  txtAlertSet8;
        alertArr[8] =  txtAlertSet9;
        alertArr[9] =  txtAlertSet10;



        Path path = Paths.get("C:/fxfile/Alert.txt");
        try {
            long lineCount = Files.lines(path).count();
            File file = new File("C:/fxfile/Alert.txt");
            if (file.exists()) {
                BufferedReader inFile = new BufferedReader(new FileReader(file));
                String sLine = null;
                for (int i = 0; i < lineCount; i++) {
                    sLine = inFile.readLine();
                    alertArr[i].setText(sLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void alertSetRemove(ActionEvent actionEvent) throws IOException {
        CheckBox[] chkAlert_Arr = new CheckBox[10];
        chkAlert_Arr[0] =  chkAlert1;
        chkAlert_Arr[1] =  chkAlert2;
        chkAlert_Arr[2] =  chkAlert3;
        chkAlert_Arr[3] =  chkAlert4;
        chkAlert_Arr[4] =  chkAlert5;
        chkAlert_Arr[5] =  chkAlert6;
        chkAlert_Arr[6] =  chkAlert7;
        chkAlert_Arr[7] =  chkAlert8;
        chkAlert_Arr[8] =  chkAlert9;
        chkAlert_Arr[9] =  chkAlert10;

        for(int i=0; i< chkAlert_Arr.length; i++) {
            if(chkAlert_Arr[i].isSelected()) {
                if(alertArr[i].getText()==null||(alertArr[i].getText().equals(""))) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("경고");
                    alert.setHeaderText("Look, Warning");
                    alert.setContentText("이미 삭제되어 있는 Alert입니다.");
                    alert.showAndWait();
                }
                else {
                    File file = new File("C:\\fxfile\\Alert.txt");
                    String path = "C:\\fxfile";
                    String arrAlert[] = new String[10];
                    readFile(file, path, arrAlert); //read해서 arrAlert배열 안에다가 넣는거임.
                    String[] result = deleteAlert(arrAlert, alertArr[i].getText());
                    writeFile(file, result);
                    alertArr[i].setText("");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("알림");
                    alert.setHeaderText("Look, CONFIRMATION");
                    alert.setContentText("해당 알림이 삭제되었습니다.");
                    alert.showAndWait();

                    //여기서 신호보내는걸 취소해야 함.
                    TimerStore.timer.stop();
                }
            }
        }
    }

    public void writeFile(File file,String[] arr) throws IOException {
        new FileOutputStream(file).close();
        FileOutputStream fos =  new FileOutputStream(file,true);
        String item="";
        for (int i=0;i<arr.length;i++){
            if (arr[i]!=null&&!arr[i].equals("")) {
                item=arr[i]+"\n";
                fos.write(item.getBytes());
            }
        }
        fos.close();
    }

    public String[] deleteAlert(String[] arrAlert, String target){
        String[] result= new String[10];
        boolean success = false;
        for (int i=0;i<result.length;i++){
            if(arrAlert[i]!=null&&arrAlert[i].equals(target)) {
                for (int j=i; j+1<result.length;j++){
                    result[j]=arrAlert[j+1];
                }
                success=true;
                break;
            }
            else result[i]=arrAlert[i];
        }
        return result;
    }

    public void readFile(File file, String path,String[] arr) throws IOException {
        File Folder = new File(path);
        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!Folder.exists()) {
            try{
                Folder.mkdir(); //폴더 생성합니다.
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }

        file.createNewFile();//파일 생성, 만약 이미 있으면 있는거 사용.

        FileReader filereader = new FileReader(file);
        BufferedReader bufReader = new BufferedReader(filereader);

        String line = "";
        int i = 0;
        while((line = bufReader.readLine()) != null){
            arr[i]=line;
            i++;
        }

        bufReader.close();
        filereader.close();
    }

    public void moveMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("Main.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load(), 670, 350);
        StageStore.stage.setTitle("Foreign exchange information provision system");
        StageStore.stage.setScene(scene1);
        StageStore.stage.show();
    }
}
