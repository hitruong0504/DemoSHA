package org.demoSHA;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteSource;

import java.io.File;
import java.io.IOException;

import static com.google.common.io.Files.asByteSource;

public class SHAHashing {
    public static void main(String[] args) throws Exception {
        System.out.println("Demo SHA");
    }

    public static String getSHAHash(String input) {
        return Hashing.sha256().hashBytes(input.getBytes()).toString();
    }

    public static String getSHAHash(File file) throws IOException {
        ByteSource byteSource = asByteSource(file);
        HashCode hashCode = byteSource.hash(Hashing.sha256());
        return hashCode.toString();
    }
}

