//package edu.uw.os.broker;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.Test;
//
//class OrderQueueTest {
//
//	@Test
//	void test() {
////		fail("Not yet implemented");
//	}
//
//}

package edu.uw.os.broker;

import test.AbstractOrderQueueTest;
import edu.uw.ext.framework.broker.OrderQueue;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

import edu.uw.ext.framework.order.Order;
import edu.uw.ext.framework.order.StopBuyOrder;
import edu.uw.ext.framework.order.StopSellOrder;

/*****************************************************************************
 * Replace these imports with the import of your implementing classes.       *
 *****************************************************************************/
import edu.uw.os.broker.SimpleOrderQueue;

/**
 * Concrete subclass of AbstractQueueTest, provides implementations of the 
 * createStopBuyOrderQueue, createStopSellOrderQueue and createAnyOrderQueue
 * methods which create instances of "my" OrderQueue implementation class, using
 * "my" Comparator implementations.
 * @param <E>
 * @param <T>
 */
public class OrderQueueTest extends AbstractOrderQueueTest {
	
	
	
    /**
     * Creates an instance of "my" OrderQueue implementation class, using
     * an instance of "my" implementation of Comparator that is intended to
     * order StopBuyOrders.
     *
     * @param filter the OrderDispatch filter to be used
     * 
     * @return a new OrderQueue instance
     */
    @Override
    protected final OrderQueue<Integer,StopBuyOrder> createStopBuyOrderQueue(
                        final BiPredicate<Integer, StopBuyOrder> filter) {
        /*********************************************************************
         * This needs to be an instance of your OrderQueue and Comparator.   *
         *********************************************************************/
        final Comparator<StopBuyOrder> ascending = Comparator.naturalOrder();
        return new SimpleOrderQueue<>(0, filter, ascending);
    }

    /**
     * Creates an instance of "my" OrderQueue implementation class, using
     * an instance of "my" implementation of Comparator that is intended to
     * order StopSellOrders.
     *
     * @param filter the OrderDispatch filter to be used
     * 
     * @return a new OrderQueue instance
     */
    @Override
    protected final OrderQueue<Integer,StopSellOrder> createStopSellOrderQueue(
                          final BiPredicate<Integer, StopSellOrder> filter) {
        /*********************************************************************
         * This needs to be an instance of your OrderQueue and Comparator.   *
         *********************************************************************/
        final Comparator<StopSellOrder> descending = Comparator.reverseOrder();
        return new SimpleOrderQueue<>(0, filter, descending);
//        return new SimpleOrderQueue<>(0, Integer t, filter, descending));}
    }							
    /**
     * Creates an instance of "my" OrderQueue implementation class, the queue
     * will order the Orders according to their natural ordering.
     *
     * @param filter the OrderDispatch filter to be used
     * 
     * @return a new OrderQueue instance
     */
    @Override
    protected final OrderQueue<Boolean,Order> createAnyOrderQueue(
                            final BiPredicate<Boolean, Order> filter) {
        /*********************************************************************
         * This needs to be an instance of your OrderQueue.                  *
         *********************************************************************/
        return new SimpleOrderQueue<Boolean, Order>(true, (Boolean t, Order o)->t);
    }

}
