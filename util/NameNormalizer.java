package Notes.util;

import java.text.Normalizer;

public class NameNormalizer {

    public static String normalize(String title){
        if (title == null){
            return "Untitled";
        }

        String normalizedName = title.trim().toLowerCase();

        normalizedName = Normalizer.normalize(normalizedName, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        normalizedName = normalizedName.replaceAll("\\s+", "_");
        normalizedName = normalizedName.replaceAll("[^a-z0-9_-]", "");
        normalizedName = normalizedName.replaceAll("_+", "_");        

        return normalizedName;
    }

}
