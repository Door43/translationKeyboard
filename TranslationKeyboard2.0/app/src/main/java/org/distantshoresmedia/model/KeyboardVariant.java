/* Generated by JavaFromJSON */
/*http://javafromjson.dashingrocket.com*/

package org.distantshoresmedia.model;

import android.inputmethodservice.Keyboard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class KeyboardVariant {

    static final private String kNameKey = "name";
    static final private String kCreatedKey = "created_at";
    static final private String kUpdatedKey = "updated_at";
    static final private String kKeyboardPositionRowKey = "key_position_rows";
    static final private String kKeyboardPositionCollumnKey = "key_position_columns";

    private String name;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

	private String createdAt;
 	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getCreatedAt() {
		return createdAt;
	}

    private String updatedAt;
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
    public String getUpdatedAt() {
        return updatedAt;
    }

	private ArrayList<KeyPosition[]> keys;
    public ArrayList<KeyPosition[]> getKeys() {
        return keys;
    }
    // 	public void setKeys(KeyPosition[] keys) {
//		this.keys = keys;
//	}
//	public KeyPosition[] getKeysAtIndex(int index) {
//        return this.keys.get(index);
//    }

    public int getNumberOfRows(){

        return keys.size();
    }

    public int[] getNumberOfKeysForRows(){

        int[] numberOfKeys = new int[keys.size()];

        for(int i = 0; i < keys.size(); i++){
            numberOfKeys[i] = keys.get(i).length;
        }

        return numberOfKeys;
    }

    public KeyboardVariant(String name, String created, String updated, ArrayList<KeyPosition[]> keys){
        this.name = name;
        this.createdAt = created;
        this.updatedAt = updated;
        this.keys = keys;
    }

    static public KeyboardVariant getKeyboardFromJsonObject(JSONObject jsonObj){

        System.out.println("Got to KeyboardVariant");

        try {
            // basic elements
            String name = jsonObj.getString(kNameKey);
            String created = jsonObj.getString(kCreatedKey);
            String updated = jsonObj.getString(kUpdatedKey);

            // Get an arraylist of keypositions based on the JSON
            JSONArray rows = jsonObj.getJSONArray(kKeyboardPositionRowKey);
            ArrayList<KeyPosition[]> positions = new ArrayList<KeyPosition[]>();

            int total = 0;

            for(int i = 0; i < rows.length(); i++){
                JSONObject rowObj = rows.getJSONObject(i);
                JSONArray columns = rowObj.getJSONArray(kKeyboardPositionCollumnKey);

                KeyPosition[] row = new KeyPosition[columns.length()];

                if(columns.length() > 0) {
                    for (int j = 0; j < columns.length(); j++) {
                        JSONObject colObj = columns.getJSONObject(j);
                        row[j] = KeyPosition.getKeyboardFromJsonObject(colObj, i, j);
                        total++;
                    }
                }
                positions.add(i, row);
            }


            KeyboardVariant finalVariant = new KeyboardVariant(name, created, updated, positions);

            return finalVariant;
        }

        catch (JSONException e) {
            System.out.println("bummer");
            System.out.println("KeyboardVariant JSONException: " + e.toString());
            return null;
        }
    }

    @Override
    public String toString() {
        return "KeyboardVariant{" +
                ", name='" + name + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", keys=" + keys +
                '}';
    }

}