import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class RESOURCES {

    public static boolean is_Older_Than(Path file_path, int time_period){
        try{
            BasicFileAttributes file_attributes = Files.readAttributes(file_path, BasicFileAttributes.class);
            LocalDateTime lastModifiedTime = LocalDateTime.ofInstant(file_attributes.lastModifiedTime().toInstant(), ZoneId.systemDefault());
            LocalDateTime time_period_ago = LocalDateTime.now().minusMonths(time_period);
            return lastModifiedTime.isBefore(time_period_ago);
        } catch(IOException e){
            return false;
        }
    }

    public static boolean is_Target_File(Path file_path){
        String file_name = file_path.getFileName().toString().toLowerCase();
        for(String extension : UNUSED_GUI.doctype_list){
            if(file_name.endsWith(extension)){
                return true;
            }
        }
        return false;
    }

    public static boolean delete_file(Path file_path){
        return file_path.toFile().delete();
    }
}
