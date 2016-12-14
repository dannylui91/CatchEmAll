package nyc.c4q.jonathancolon.catchemall.prisoner;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by jonathancolon on 12/14/16.
 */

public class PrisonerSerializer {

        public void serializePrisoner (Prisoner p){
            try
            {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("/sdcard/save_object.bin"))); //Select where you wish to save the file...
                oos.writeObject(p); // write the class as an 'object'
                oos.flush(); // flush the stream to insure all of the information was written to 'save_object.bin'
                oos.close();// close the stream
            }
            catch(Exception ex)
            {
                Log.v("Serialize Save Error : ",ex.getMessage());
                ex.printStackTrace();
            }
        }

        public Object loadSerializedPrisoner (File f)
        {
            try
            {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
                Object o = ois.readObject();
                return o;
            }
            catch(Exception ex)
            {
                Log.v("Serialize Read Error : ",ex.getMessage());
                ex.printStackTrace();
            }
            return null;
        }
}
