package ucr.ac.cr.ecci.ci1221.util.algorithm;

/**
 * @author Rodrigo A. Bartels
 */
public class StringAlgorithms {

    public static int naiveStringMatcher(String text, String pattern){
        int textCounter = 0;
        int foundIndex = -1;
        while(textCounter < text.length() && text.charAt(textCounter) != pattern.charAt(0))
            textCounter++;

        int patternCounter = 0;
        for(int i = textCounter; i < text.length() && patternCounter < pattern.length(); i++){
            if(patternCounter < pattern.length() && text.charAt(i) == pattern.charAt(patternCounter)) {
                if(patternCounter == 0)
                    foundIndex = i;

                patternCounter++;
            }
            else {
                patternCounter = 0;
                foundIndex = -1;
            }
        }
        return foundIndex;
    }

    public static int knuthMorrisPrattStringMatcher(String text, String pattern){

        return 0;
    }

    public static int boyerMooreStringMatcher(String text, String pattern){

        return 0;
    }
}
