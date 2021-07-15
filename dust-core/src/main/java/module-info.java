import com.dust.core.event.impl.EventEmitterImpl;

module com.dust.core {
    exports com.dust.core.enums;
    exports com.dust.core.event;
    exports com.dust.core.eventloop;
    exports com.dust.core.task;
    exports com.dust.core.scene;
    exports com.dust.core.stage;
    provides com.dust.core.event.EventEmitter with EventEmitterImpl;
    uses com.dust.core.event.EventEmitter;
}