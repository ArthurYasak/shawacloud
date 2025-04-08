package com.shawa;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.ProtocolVersion;
import com.datastax.oss.driver.api.core.type.DataType;
import com.datastax.oss.driver.api.core.type.UserDefinedType;
import com.datastax.oss.driver.api.core.type.codec.TypeCodec;
import com.datastax.oss.driver.api.core.type.reflect.GenericType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.ByteBuffer;

public class ShawaUDTCodec implements TypeCodec<ShawaUDT> {

    private final UserDefinedType shawaUDT;

    public ShawaUDTCodec(CqlSession session) {
        this.shawaUDT = session.getMetadata()
                .getKeyspace("shawacloud")
                .flatMap((k) -> k.getUserDefinedType("shawa"))
                .get();
    }

    @NotNull
    @Override
    public GenericType<ShawaUDT> getJavaType() {
        return GenericType.of(ShawaUDT.class);
    }

    @NotNull
    @Override
    public DataType getCqlType() {
        return shawaUDT;
    }

    @Nullable
    @Override
    public ByteBuffer encode(@Nullable ShawaUDT value, @NotNull ProtocolVersion protocolVersion) {
        if (value == null) {
            return null;
        }
        throw new UnsupportedOperationException("Encoding not implemented");
    }

    @Nullable
    @Override
    public ShawaUDT decode(@Nullable ByteBuffer bytes, @NotNull ProtocolVersion protocolVersion) {
        if (bytes == null) {
            return null;
        }
        throw new UnsupportedOperationException("Decoding not implemented");
    }

    @NotNull
    @Override
    public String format(@Nullable ShawaUDT value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    @Nullable
    @Override
    public ShawaUDT parse(@Nullable String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        throw new UnsupportedOperationException("Parsing not implemented");
    }

}