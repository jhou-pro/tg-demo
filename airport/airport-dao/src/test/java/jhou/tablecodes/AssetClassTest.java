package jhou.tablecodes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import jhou.test_config.AbstractDaoTestCase;
import jhou.test_config.UniversalConstantsForTesting;
import ua.com.fielden.platform.utils.IUniversalConstants;

public class AssetClassTest extends AbstractDaoTestCase {
    
    @Test
    public void some_predicates_on_abstract_entities() {
        final AssetClass ac1 = co(AssetClass.class).findByKey("AC1");
        assertNotNull(ac1);
        assertEquals("AC1", ac1.getKey().toString());
        
        assertTrue(ac1.isPersistent());
        assertTrue(ac1.isPersisted());
        
        final IAssetClass coAssetClass = co(AssetClass.class);
        final AssetClass newAc3 = coAssetClass.new_().setName("AC3");
        newAc3.setDesc("new ac3");
        assertNotNull(newAc3);
        assertEquals("AC3", newAc3.getKey().toString());
        
        assertTrue(newAc3.isPersistent());
        assertFalse(newAc3.isPersisted());
    }
    
    @Test
    public void dirty_and_valid_predicates_on_abstract_entities() {
        final AssetClass ac1 = co$(AssetClass.class).findByKey("AC1");
        
        assertFalse(ac1.isDirty());
        assertTrue(ac1.isValid().isSuccessful());
        
        ac1.setName("AC1");
        assertFalse(ac1.isDirty());
        assertTrue(ac1.isValid().isSuccessful());
        
        ac1.setName("AC42");
        assertTrue(ac1.isDirty());
        assertTrue(ac1.isValid().isSuccessful());
        
        ac1.setName("AC1");
        assertFalse(ac1.isDirty());
        assertTrue(ac1.isValid().isSuccessful());
        
        final AssetClass ac42 = co$(AssetClass.class).save(ac1.setName("AC42"));
        assertFalse(ac42.isDirty());
        ac42.setName("AC1");
        assertTrue(ac42.isDirty());
    }
    
    @Test
    public void createdBy_information_is_assigned_upon_saving() {
        final AssetClass ac1 = co$(AssetClass.class).findByKey("AC1");
        assertNotNull(ac1.getCreatedBy());
        
        final AssetClass ac42 = co$(AssetClass.class).new_().setName("AC42");
        ac42.setDesc("Description");
        final AssetClass ac42saved = co$(AssetClass.class).save(ac42);
        
        assertNotNull(ac42saved.getCreatedBy());
        assertNull(ac42saved.getLastUpdatedBy());
        System.out.println("createdBy = " + ac42.getCreatedBy());
        System.out.println("lastUpdatedBy = " + ac42.getLastUpdatedBy());
    }
    
    /**
     * In case of a complex data population it is possible to store the data into a script by changing this method to return <code>true</code>.
     * <p>
     * This way it is possible to reuse it later in place of re-running the data population logic, which is a lot faster.
     * Please also refer method {@link #useSavedDataPopulationScript()} below -- the values returned by this and that mathod cannot be <code>true</code> simultaneously.
     */
    @Override
    public boolean saveDataPopulationScriptToFile() {
        return false;
    }
    
    /**
     * If the test data was populated and saved as a script file (hinted in method {@link #saveDataPopulationScriptToFile()} above),
     * then this method can be changed to return <code>true</code> in order to avoid execution of the data population logic and simply execute the saved script.
     * This makes the population of the test data a lot faster.
     * It is very convenient when there is a need to run the same test case multiple times interactivelly.
     * <p>
     * However, this method should never return <code>true</code> when running multiple test cases.
     * Therefore, it is imporant to change this method to return <code>false</code> before committing changes into your VCS such as Git.
     */
    @Override
    public boolean useSavedDataPopulationScript() {
        return false;
    }
    
    /**
     * Domain state population method.
     * <p>
     * <b>IMPORTANT:</p> this method executes only once for a Test Case. At the same time, new instances of a Test Case are created for each test method.
     * Thus, this method should not be used for initialisation of the Test Case state other than the persisted domain state.
     */
    @Override
    protected void populateDomain() {
        // Need to invoke super to create a test user that is responsible for data population 
        super.populateDomain();
        
        // Here is how the Test Case universal constants can be set.
        // In this case the notion of now is overridden, which makes it possible to have an invariant system-time.
        // However, the now value should be after AbstractDaoTestCase.prePopulateNow in order not to introduce any date-related conflicts.
        final UniversalConstantsForTesting constants = (UniversalConstantsForTesting) getInstance(IUniversalConstants.class);
        constants.setNow(dateTime("2019-10-01 11:30:00"));
        
        // If the use of saved data population script is indicated then there is no need to proceed with any further data population logic.
        if (useSavedDataPopulationScript()) {
            return;
        }
        
        save(new_(AssetClass.class).setName("AC1").setDesc("The first asset class."));
        save(new_(AssetClass.class).setName("AC2").setDesc("The second asset class."));
    }
    
}