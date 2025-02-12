// JD Waldron
// Rule 00: IDS01-J. Normalize strings before validating them

package main.java.jd;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IDS01_J
{
    public static void main(String[] args)
    {
        String s = "\uFE64" + "script" + "\uFE65";

        // Normalize
        s = Normalizer.normalize(s, Form.NFKC);

        // Validate
        Pattern pattern = Pattern.compile("[<>]");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find())
        {
            // Found blacklisted tag
            throw new IllegalStateException();
        }
        else
        {
            // ...
        }
    }
}
