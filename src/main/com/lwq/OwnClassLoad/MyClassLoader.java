package main.com.lwq.OwnClassLoad;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @Author: Lwq
 * @Date: 2018/8/26 15:56
 * @Version 1.0
 * @Describe
 */
public class MyClassLoader extends ClassLoader{
    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    public MyClassLoader() {
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException{
        File file = getClassFile(name);
        try {
            byte[] bytes = getClassBytes(file);
            Class<?> c = this.defineClass(name,bytes,0,bytes.length);
            return c;
        }catch (Exception e){
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    private byte[] getClassBytes(File file) throws IOException {
        //读入.class的字节，使用字节流
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel wbc = Channels.newChannel(baos);
        ByteBuffer by = ByteBuffer.allocate(1024);

        while (true){
            int i = fc.read(by);
            if(i==0||i==-1){
                break;
            }
            by.flip();
            wbc.write(by);
            by.clear();
        }
        fis.close();
        return baos.toByteArray();
    }

    private byte[] getClassBytes2(File file) throws IOException {
        if(!file.exists()){
            throw new FileNotFoundException();
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1!=(len=in.read(buffer,0,buf_size))){
                bos.write(buffer,0,len);
            }
            return bos.toByteArray();
        }catch (IOException e) {
            e.printStackTrace();
            throw e;
        }finally{
            try{
                in.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }


    private File getClassFile(String name) {
        File file = new File("F:\\Workspaces\\ideaProjects\\JavaDemo\\target\\classes\\main\\com\\lwq\\OwnClassLoad\\Person.class");
        return file;
    }
}
