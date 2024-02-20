package padre.virus.serializacion;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serializador {
    private final String fileName;

    public Serializador(String fileName) {
        super();
        this.fileName = fileName;
    }

    public boolean writeOneObject(Object obj) {
        boolean respuesta = false;
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(obj);
            oos.close();
            respuesta = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    public boolean addOneObject(Object obj) {
        boolean respuesta = false;
        try {
            AddableObjectOutputStream oos = new AddableObjectOutputStream (new FileOutputStream(fileName,true));
            oos.writeObject(obj);
            oos.close();
            respuesta = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return respuesta;
    }


    public Object readFirstObject() {
        Object obj = null;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(fileName));
            obj = ois.readObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ois!= null){
                try{
                    ois.close();
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }

    public Object[] readObjects() {
        List<Object> listOfObjects = new ArrayList<>();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(fileName));
            Object obj;
            while ((obj = ois.readObject()) != null) {
                listOfObjects.add(obj);
            }
            ois.close();
        } catch (EOFException e) {
            System.out.println("Lectura exitosa!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listOfObjects.toArray();
    }
}

