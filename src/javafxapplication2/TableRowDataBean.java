/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author maksato
 */
public class TableRowDataBean {
	/** ファイル名 */
	private StringProperty name;

	/** ファイルサイズ */
	private LongProperty  size;

	/** ファイルパス */
	private StringProperty path;

	/** 更新日付 */
	private StringProperty update;
    
    /**
	 * コンストラクタ。
	 * <p>
	 * ダウンロード画面で表示されるテーブルです。
	 * </p>
	 * @param name ファイル名
	 * @param size ファイルサイズ
     * @param path フルパス
	 * @param update 更新日付
	 */
	public TableRowDataBean(String name, long  size, String path, String update) {
		this.name =  new SimpleStringProperty(name);
		this.size =  new SimpleLongProperty(size);
		this.path =  new SimpleStringProperty(path);
		this.update =  new SimpleStringProperty(update);
	}
    
    public StringProperty nameProperty() {return name;}
    public LongProperty sizeProperty() {return size;}
    public StringProperty pathProperty() {return path;}
    public StringProperty updateProperty() {return update;}
}
