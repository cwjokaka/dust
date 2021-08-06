import com.dust.core.event.impl.EventSourceImpl;

module com.dust.core {
    exports com.dust.core.event;
    exports com.dust.core.eventloop;
    exports com.dust.core.task;
    exports com.dust.core.scene;
    exports com.dust.core.stage;
    exports com.dust.core.annotation;
    exports com.dust.core.eventloop.impl;

    provides com.dust.core.event.EventSource with EventSourceImpl;
    uses com.dust.core.event.EventSource;
    uses com.dust.core.event.EventQueue;
}