import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /*
     * CONSTRUCTOR TESTS -------------------------------------------------------
     */

    /*
     * NOTE: There are not any alternative constructors for Set -- we can test
     * add indirectly by putting parameters into our constructor functions.
     */

    /**
     * Test the set constructor with no parameters.
     */
    @Test
    public final void constructorEmptyTest() {
        /*
         * ASSIGN
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * ASSERT
         */
        assertEquals(s, sExpected);
    }

    /*
     * ADD TESTS ---------------------------------------------------------------
     */

    /**
     * Test adding to an empty set.
     */
    @Test
    public final void addToEmptyTest() {
        /*
         * ASSIGN
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("Test");
        /*
         * CALL
         */
        s.add("Test");
        /*
         * ASSERT
         */
        assertEquals(s, sExpected);
    }

    /**
     * Test adding to a single-element set.
     */
    @Test
    public final void addToSingleTest() {
        /*
         * ASSIGN
         */
        Set<String> s = this.createFromArgsTest("Test");
        Set<String> sExpected = this.createFromArgsRef("Test", "This");
        /*
         * CALL
         */
        s.add("This");
        /*
         * ASSERT
         */
        assertEquals(s, sExpected);
    }

    /**
     * Test adding to a set with multiple elements.
     */
    @Test
    public final void addToSetWithMultipleEntriesTest() {
        /*
         * ASSIGN
         */
        Set<String> s = this.createFromArgsTest("Test", "This", "My", "Dude",
                "Wow", "Cheese", "Is", "Great");
        Set<String> sExpected = this.createFromArgsRef("Test", "This", "My",
                "Dude", "Wow", "Cheese", "Is", "Great", "Right?");
        /*
         * CALL
         */
        s.add("Right?");
        /*
         * ASSERT
         */
        assertEquals(s, sExpected);
    }

    /*
     * REMOVE TESTS ------------------------------------------------------------
     */

    /**
     * Test remove from a single-element set.
     */
    @Test
    public final void removeFromSingleTest() {
        /*
         * ASSIGN
         */
        Set<String> s = this.createFromArgsTest("Test");
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * CALL
         */
        String removed = s.remove("Test");
        /*
         * ASSERT
         */
        assertEquals(removed, "Test");
        assertEquals(s, sExpected);
    }

    /**
     * Test remove from a set with multiple entries.
     */
    @Test
    public final void removeFromSetWithMultipleEntriesTest() {
        /*
         * ASSIGN
         */
        Set<String> s = this.createFromArgsTest("Test", "Wow", "Alex", "Ben");
        Set<String> sExpected = this.createFromArgsRef("Test", "Wow", "Ben");
        /*
         * CALL
         */
        String removed = s.remove("Alex");
        /*
         * ASSERT
         */
        assertEquals(removed, "Alex");
        assertEquals(s, sExpected);
    }

    /*
     * REMOVE ANY TESTS --------------------------------------------------------
     */

    /**
     * Test removeAny from a single-element set.
     */
    @Test
    public final void removeAnyFromSingleTest() {
        /*
         * ASSIGN
         */
        Set<String> s = this.createFromArgsTest("Test");
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * CALL
         */
        String removed = s.removeAny();
        /*
         * ASSERT
         */
        assertEquals(removed, "Test");
        assertEquals(s, sExpected);
    }

    /**
     * Test removeAny from a set with multiple values.
     */
    @Test
    public final void removeAnyFromSetWithMultipleValuesTest() {
        /*
         * ASSIGN
         */
        Set<String> s = this.createFromArgsTest("Test", "Alex", "Ben", "Zohan");
        Set<String> sExpected = this.createFromArgsRef("Test", "Alex", "Ben",
                "Zohan");
        /*
         * CALL
         */
        String removed = s.removeAny();
        /*
         * TEST ASSERTS
         */
        assertEquals(sExpected.contains(removed), true);
        /*
         * TEST CALLS
         */
        sExpected.remove(removed);
        /*
         * ASSERT
         */
        assertEquals(s, sExpected);
    }

    /*
     * CONTAINS TESTS ----------------------------------------------------------
     */

    /**
     * Test contains on an empty set.
     */
    @Test
    public final void containsEmptyTest() {
        /*
         * ASSIGN
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * CALL
         */
        boolean contains = s.contains("Test");
        /*
         * ASSERT
         */
        assertEquals(sExpected, s);
        assertEquals(contains, false);
    }

    /**
     * Test contains on a single-element set.
     */
    @Test
    public final void containsSingleTest() {
        /*
         * ASSIGN
         */
        Set<String> s = this.createFromArgsTest("Test");
        Set<String> sExpected = this.createFromArgsRef("Test");
        /*
         * CALL
         */
        boolean contains = s.contains("Test");
        /*
         * ASSERT
         */
        assertEquals(sExpected, s);
        assertEquals(contains, true);
    }

    /**
     * Test contains on a set with multiple values.
     */
    @Test
    public final void containsSetWithMultipleValuesTest() {
        /*
         * ASSIGN
         */
        Set<String> s = this.createFromArgsTest("Test", "Wow", "Isn't", "This",
                "Great?");
        Set<String> sExpected = this.createFromArgsRef("Test", "Wow", "Isn't",
                "This", "Great?");
        /*
         * CALL
         */
        boolean contains = s.contains("Wow");
        /*
         * ASSERT
         */
        assertEquals(sExpected, s);
        assertEquals(contains, true);
    }

    /**
     * Test contains on a set with multiple values (returns false).
     */
    @Test
    public final void containsSetWithMultipleValuesShouldReturnFalseTest() {
        /*
         * ASSIGN
         */
        Set<String> s = this.createFromArgsTest("Test", "Wow", "Isn't", "This",
                "Great?");
        Set<String> sExpected = this.createFromArgsRef("Test", "Wow", "Isn't",
                "This", "Great?");
        /*
         * CALL
         */
        boolean contains = s.contains("Cool!");
        /*
         * ASSERT
         */
        assertEquals(sExpected, s);
        assertEquals(contains, false);
    }

    /*
     * SIZE TESTS --------------------------------------------------------------
     */

    /**
     * Test size on an empty set.
     */
    @Test
    public final void sizeEmptyTest() {
        /*
         * ASSIGN
         */
        Set<String> s = this.createFromArgsTest();
        /*
         * CALL
         */
        int size = s.size();
        /*
         * ASSERT
         */
        assertEquals(size, 0);
    }

    /**
     * Test size on a single-element set.
     */
    @Test
    public final void sizeSingleTest() {
        /*
         * ASSIGN
         */
        Set<String> s = this.createFromArgsTest("Cool");
        /*
         * CALL
         */
        int size = s.size();
        /*
         * ASSERT
         */
        assertEquals(size, 1);
    }

    /**
     * Test size on a set with multiple values.
     */
    @Test
    public final void sizeSetWithMultipleValuesTest() {
        /*
         * ASSIGN
         */
        Set<String> s = this.createFromArgsTest("Cool", "Beans", "This", "Is",
                "Sweet!");
        /*
         * CALL
         */
        int size = s.size();
        /*
         * ASSERT
         */
        final int expectedSize = 5;
        assertEquals(size, expectedSize);
    }

}
