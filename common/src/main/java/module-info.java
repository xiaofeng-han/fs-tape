module common {
    requires java.logging;
    requires java.net.http;
    requires java.xml;
    requires java.scripting;
    requires org.apache.commons.collections4;
    requires org.apache.commons.lang3;
    requires static org.mapstruct.processor;
    requires static lombok;
    requires slf4j.api;
    exports io.xiaofeng.fstape.common.module;
}