package appDomain;

import manager.SortManager;

/** 
 * <p> 
 * This application driver code is designed to be used as a basis for the 
 * Complexity and Sorting assignment that will be developed in the CPRG304 
 * W2026 class at SAIT. The implementors of this applications will be required 
 * to add all the correct functionality. 
 * </p>
 * 
 * @author our names
 */
public class AppDriver
{
    /**
     * Application entry point.
     *
     * <p>
     * Accepts command-line arguments that control how the sorting
     * application executes (for example: input file name, sorting
     * algorithm selection, comparator type, etc.).
     * </p>
     *
     * @param args the arguments to take in from the run configuration and pass to SortManager
     */
    public static void main(String[] args)
    {
        new SortManager(args);
    }
}