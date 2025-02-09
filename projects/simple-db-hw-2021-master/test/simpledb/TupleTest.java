package simpledb;

import static org.junit.Assert.assertEquals;
import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

import simpledb.common.Utility;
import simpledb.storage.*;
import simpledb.systemtest.SimpleDbTestBase;

public class TupleTest extends SimpleDbTestBase {

    /**
     * Unit test for Tuple.getField() and Tuple.setField()
     */
    @Test public void modifyFields() {
        TupleDesc td = Utility.getTupleDesc(2);

        Tuple tup = new Tuple(td);
        tup.setField(0, new IntField(-1));
        tup.setField(1, new IntField(0));

        assertEquals(new IntField(-1), tup.getField(0));
        assertEquals(new IntField(0), tup.getField(1));

        tup.setField(0, new IntField(1));
        tup.setField(1, new IntField(37));

        assertEquals(new IntField(1), tup.getField(0));
        assertEquals(new IntField(37), tup.getField(1));
    }

    /**
     * Unit test for Tuple.getTupleDesc()
     */
    @Test public void getTupleDesc() {
        TupleDesc td = Utility.getTupleDesc(5);
        Tuple tup = new Tuple(td);
        assertEquals(td, tup.getTupleDesc());
    }

    /**
     * Unit test for Tuple.getRecordId() and Tuple.setRecordId()
     */
    @Test public void modifyRecordId() {
        Tuple tup1 = new Tuple(Utility.getTupleDesc(1));            // 首先创建一个Tuple
        HeapPageId pid1 = new HeapPageId(0,0);          // 其次创建一个页
        RecordId rid1 = new RecordId(pid1, 0);                 // 创建页的一条记录，把记录放入到0行。
        tup1.setRecordId(rid1);                                         // 将记录引用放入到tuple中
	try {
	    assertEquals(rid1, tup1.getRecordId());                         // tuple的heap页发生了变化，判断是否成功修改。
	} catch (UnsupportedOperationException e) {
		//rethrow the exception with an explanation
    	throw new UnsupportedOperationException("modifyRecordId() test failed due to " +
    			"RecordId.equals() not being implemented.  This is not required for Lab 1, " +
    			"but should pass when you do implement the RecordId class.");
	}
    }

    /**
     * JUnit suite target
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(TupleTest.class);
    }
}

