package com.example.javafx_practice;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.util.*;
import javax.swing.Timer;

public class MainController implements Initializable {
    @FXML
    public ListView lstNationalChange;
    public ListView lstNationalRate;

    //즐겨찾기 버튼
    public Button btnBookmarkAdd;
    //즐겨찾기 버튼5개
    public Button btnBookmark1;
    public Button btnBookmark2;
    public Button btnBookmark3;
    public Button btnBookmark4;
    public Button btnBookmark5;
    //선택된 통화 임시저장 변수
    public String currencytmp = null;
    public String selectedTmp = null;
    //Alert클릭 시 alertscene에 추가
    public TextField txtAlertInput;
    public String alertAmount = null;
    // 환율변환 관련 텍스트필드
    public TextField txtExInput;
    public TextField txtExOutput;



    String[] currencyArrayList = {"AED 아랍에미리트 디르함", "AUD 호주 달러", "BHD 바레인 디나르", "BND 브루나이 달러", "CAD 캐나다 달러", "CHF 스위스 프랑",
            "CNH 위안화", "DKK 덴마아크 크로네", "EUR 유로", "GBP 영국 파운드", "HKD 홍콩 달러", "IDR 인도네시아 루피아", "JPY 일본 옌", "KWD 쿠웨이트 디나르",
            "MYR 말레이지아 링기트", "NOK 노르웨이 크로네", "NZD 뉴질랜드 달러", "SAR 사우디 리얄", "SEK 스웨덴 크로나", "SGD 싱가포르 달러", "THB 태국 바트", "USD 미국 달러"};


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lstNationalChange.setItems(FXCollections.observableArrayList(currencyArrayList));
        lstNationalChange.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String strItem = (String) lstNationalChange.getSelectionModel().getSelectedItem();
                currencytmp = strItem;
            }
        });

        lstNationalRate.setItems(FXCollections.observableArrayList(currencyArrayList));
        lstNationalRate.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String strItem = (String) lstNationalChange.getSelectionModel().getSelectedItem();
                selectedTmp = strItem;
            }
        });
    }

    public void btnBookmark(ActionEvent actionEvent) throws IOException {

    }

    public void pageMove(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("forex.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        StageStore.stage.setTitle("MAIN");
        StageStore.stage.setScene(scene);
        StageStore.stage.show();
    }

    public void alertClick(ActionEvent actionEvent) throws IOException {
        if(currencytmp==null||currencytmp.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("경고");
            alert.setHeaderText("Look, Warning");
            alert.setContentText("통화를 선택하지 않으셨습니다");
            alert.showAndWait();
            return;
        }
        String currency = currencytmp.substring(0, 3);
        alertAmount = txtAlertInput.getText(); //txtAlertInput은 입력 금액임
        if(alertAmount==null||alertAmount.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("경고");
            alert.setHeaderText("Look, Warning");
            alert.setContentText("설정금액을 입력하지 않으셨습니다");
            alert.showAndWait();
            return;
        }
        String AlertStackSet = currency + " " + alertAmount;

        String tmpArr[] = new String[]{"","","","","","","","","",""};
        File file = new File("C:\\fxfile\\Alert.txt");
        String path = "C:\\fxfile"; //폴더 경로
        readFile_Alert(file, path, tmpArr);
        if (!checkDuplicate_Alert(tmpArr, AlertStackSet)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Look, a Warning Dialog");
            alert.setContentText("이미 Alert에 추가되어 있는 항목이거나 최대 등록개수를 초과했습니다.");
            alert.showAndWait();
            return;
        }
        // 중복체크
        else {
            FileOutputStream fos =  new FileOutputStream(file,true);
            AlertStackSet += "\n";
            fos.write(AlertStackSet.getBytes());
            fos.close();

            //파일에 들어가게 되면 신호를 추가해야 함.
            swingTimer_Alert(currency, alertAmount);
        }
    }
    static void swingTimer_Alert(String currency, String alertAmount) {
        TimerStore.timer = new Timer(600000, new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
            //여기서 네트워크로 보냄
            }
        });
        TimerStore.timer.start();
    }

    public boolean checkDuplicate_Alert(String[] arr, String test) {
        if (arr[9]!=null&&!arr[9].equals("")) return false;//즐겨찾기 최대 개수 초과
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].equals("")) {
                count++;
            }//여기서 파일에서 읽어서 넣은 배열의 크기를 카운트
        }
        Set<String> set = new HashSet<String>(); // set of timeline

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("")||arr[i]==null) break;
            set.add(arr[i]);
        }
        set.add(test);
        if (count + 1 != set.size()) {
            return false;
        }
        return true;
    }

    public void AlertPageMove(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AlertStack.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 390);
        StageStore.stage.setTitle("AlertStack");
        StageStore.stage.setScene(scene);
        StageStore.stage.show();
    }

    public void readFile_Alert(File file, String path, String[] arr) throws IOException {
        File Folder = new File(path);
        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!Folder.exists()) {
            try {
                Folder.mkdir(); //폴더 생성합니다.
            } catch (Exception e) {
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

    public void btnChk_Calculate(ActionEvent actionEvent) {
        //여기서는 selectedTmp(선택된 통화), inputCurrency(입력한 가격)을 네트워크로 보냄
        String inputCurrency = txtExInput.getText();
        int currentExchange = Integer.parseInt(inputCurrency);


        txtExOutput.setText(String.valueOf(currentExchange));
    }
}