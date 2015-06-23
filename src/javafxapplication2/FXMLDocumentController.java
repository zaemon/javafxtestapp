/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 *
 * @author maksato
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private ChoiceBox<String> choicebox;
    
    @FXML
    private Button button;
    
    @FXML
    private TableView<TableRowDataBean> table1;
    
    @FXML
    private TableColumn<TableRowDataBean,String> name;
    
    @FXML
    private TableColumn<TableRowDataBean,Long> size;
    
    @FXML
    private TableColumn<TableRowDataBean,String> path;
    
    @FXML
    private TableColumn<TableRowDataBean,String> update;

    private ObservableList<TableRowDataBean> rowDataList;
    
    private int state;
    
    private final static int WINHOMEDIR = 1;
    private final static int CYGHOMEDIR = 2;

    /** 日付フォーマット */
	private final static String DATE_PATTERN = "yyyy/MM/dd HH:mm:ss zzz";

    public FXMLDocumentController() {
        this.rowDataList = FXCollections.observableArrayList();
    }
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
		toggle();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initLclTableView();
        state = WINHOMEDIR;
    }    
    
    private void initLclTableView() {

		// ローカルテーブル 活性・複数選択可能
		table1.setEditable(true);
		table1.selectionModelProperty().get().setSelectionMode(SelectionMode.MULTIPLE);

		// ファイル名
		PropertyValueFactory<TableRowDataBean, String> namePropertyValueFactory = new PropertyValueFactory<>("name");
		name.setCellValueFactory(namePropertyValueFactory);

		// ファイルサイズ
		PropertyValueFactory<TableRowDataBean, Long> sizePropertyValueFactory = new PropertyValueFactory<>("size");
		size.setCellValueFactory(sizePropertyValueFactory);

		// フルパス
		PropertyValueFactory<TableRowDataBean, String> pathPropertyValueFactory = new PropertyValueFactory<>("path");
		path.setCellValueFactory(pathPropertyValueFactory);

		// 最終更新日時
		PropertyValueFactory<TableRowDataBean, String> updatePropertyValueFactory = new PropertyValueFactory<>("update");
		update.setCellValueFactory(updatePropertyValueFactory);
        
        toggle();
	}

    private void toggle() {
        if (this.state == WINHOMEDIR) {
            display("c:/Users/maksato");
            this.state = CYGHOMEDIR;
        } else {
            display("c:/Users/maksato/.android");
            this.state = WINHOMEDIR;
        }
    }
    
    private void display (String path) {
        File home = new File(path);
        File[] subFiles = home.listFiles();
        
        for(File subFile: subFiles) {
            	TableRowDataBean rowData = new TableRowDataBean(subFile.getName(), subFile.length(), subFile.getPath(), DateFormatUtils.format(new Date(subFile.lastModified()), DATE_PATTERN));
				rowDataList.add(rowData);
        }
        table1.getItems().clear();
        table1.setItems(rowDataList);
        table1.layout();
    }
    
}

