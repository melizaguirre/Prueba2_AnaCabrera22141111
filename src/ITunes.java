
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author BAC
 */
public class ITunes {

    File dir = new File("\\Usuarios\\Desktop\\Ejercicio Java\\Prueba2_AnaCabrera22141111");
    File codigos = new File(dir.getPath() + "\\Codigos.itn");
    File songs = new File(dir.getPath() + "\\Songs.itn");
    File downloads = new File(dir.getPath() + "\\Downloads.itn");
    RandomAccessFile code, song, download;

    public ITunes() throws FileNotFoundException, IOException {
        dir.mkdir();
        code = new RandomAccessFile(codigos, "rw");
        song = new RandomAccessFile(songs, "rw");
        download = new RandomAccessFile(downloads, "rw");
        if (code.length() == 0) {
            code.writeInt(1);
            code.writeInt(1);
        }
    }

    public int getCodigo(long offset) throws IOException {
        code.seek(0);
        int cancion = code.readInt();
        int descarga = code.readInt();
        if (songs.length() != 0) {
            if (offset == 0) {
                return ++cancion;
            } else if (offset == 4) {
                return ++descarga;
            }
        } else if (songs.length() != 0 && download.length() == 0) {
            if (offset == 0) {
                return ++cancion;
            } else if (offset == 4) {
                return descarga;
            }
        } else {
            if (offset == 0) {
                return cancion;
            } else if (offset == 4) {
                return descarga;
            }
        }
        return -1;
    }
     public void addSong(String nombre, String cantante, double precio) throws IOException
    {
        code.seek(0);
        int cancion = code.readInt();
        int descarga = code.readInt();
        if (song.length() != 0)  {
            song.seek(song.length());
            song.writeInt(++cancion);
            song.writeUTF(nombre);
            song.writeUTF(cantante);
            song.writeDouble(precio);
            song.writeInt(0);
            song.writeInt(0);
        }
        else
        {
            song.seek(song.length());
            song.writeInt(cancion);
            song.writeUTF(nombre);
            song.writeUTF(cantante);
            song.writeDouble(precio);
            song.writeInt(0);
            song.writeInt(0);
        }
        code.seek(0);
        code.writeInt(++cancion);
        code.writeInt(descarga);
    }
    
    public void reviewsSong(int code, int stars) throws IOException
    {
        song.seek(0);
        while(song.getFilePointer() < song.length())
        {
            int cod = song.readInt();
            String nom = song.readUTF();
            String cant = song.readUTF();
            double precio = song.readDouble();
            long posicion = song.getFilePointer();
            int star = song.readInt();
            int review = song.readInt();
            try{
            if (code == cod) {
                if (stars >= 0 && stars <= 5) {
                    star += stars;
                    review = ++review;
                    song.seek(posicion);
                    song.writeInt(star);
                    song.writeInt(review);
                }
            }
 
            } catch (IllegalArgumentException e){
                 e.printStackTrace();
                System.out.println("Código de canción invalido");
            } 
        }
    }
    
    public void downloadSong(int codeSong, String cliente) throws IOException
    {
        code.seek(0);
        int cancion = code.readInt();
        int descarga = code.readInt();
        
        song.seek(0);
        while(song.getFilePointer() < song.length()){
            int cod = song.readInt();
            String nom = song.readUTF();
            String cant = song.readUTF();
            double precio = song.readDouble();
            int star = song.readInt();
            int review = song.readInt();
            if (codeSong == cod) {
                download.seek(download.length());
                if (download.length() != 0) {
                    download.writeInt(++descarga);
                    download.writeLong(Calendar.getInstance().getTimeInMillis());
                    download.writeInt(cod);
                    download.writeUTF(cliente);
                    download.writeDouble(precio);
                }
                else{
                    download.writeInt(descarga);
                    download.writeLong(Calendar.getInstance().getTimeInMillis());
                    download.writeInt(cod);
                    download.writeUTF(cliente);
                    download.writeDouble(precio);
                }
                code.seek(0);
                code.writeInt(cancion);
                code.writeInt(++descarga);
                return;
                
                //System.out.println("Gracias" + cliente + "Por bajar" + nom + "a un  costo de lps"+ precio);
            }
            else{
                System.out.println("Código de canción inválido.");
            }
        }
    }
    
    public void songs(String txtFile) throws FileNotFoundException, IOException {

        PrintWriter escritura = new PrintWriter(txtFile);
        escritura.print("");
        escritura.close();

        FileReader canciones = new FileReader(txtFile);
        
        while(song.getFilePointer()<songs.length()) {
            int cod = song.readInt();
            String nom = song.readUTF();
            String cant = song.readUTF();
            double precio = song.readDouble();
            double star = song.readInt();
            int review = song.readInt();
            
            Double ratingfinal = star/review;

            System.out.println("");
            System.out.println("Codigo: " + cod + "Cantante: " + cant+"Precio: " + precio + "Rating: " + ratingfinal);
        }
    }
    public void infoSong(int codeSong) throws IOException{
        int descargas = 0;
        song.seek(0);
        download.seek(0);
        
        while(song.getFilePointer() < song.length()){
            int cod = song.readInt();
            String nom = song.readUTF();
            String cant = song.readUTF();
            double precio = song.readDouble();
            int star = song.readInt();
            int review = song.readInt();
            if (codeSong == cod)  {
                download.seek(0);
                while(download.getFilePointer() < download.length()){
                    int codD = download.readInt();
                    long date = download.readLong();
                    int codCan = download.readInt();
                    String nomCliente = download.readUTF();
                    double precioCan = download.readDouble();
                    if (codCan == codeSong) 
                    {
                        descargas++;
                    }
                }
                System.out.println("Código de la canción: " + cod + " - Titulo:" + nom + " - Cantante: " + cant  
                        + " - Precio: " + precio + " - Estrellas recibidas: " + star + " - Cantidad de reviews recibidos: " + review+ " - Rating: " + (star/review));
            }
        }
    }
}
