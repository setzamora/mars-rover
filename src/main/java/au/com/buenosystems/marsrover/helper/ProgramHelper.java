package au.com.buenosystems.marsrover.helper;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProgramHelper {
    public static Path getTerrainPath(String[] args) throws URISyntaxException {
        Path path;
        if (isArgumentSpecified(args)) {
            path = Paths.get(args[0]);
        } else {
            final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            final URI uri = classLoader.getResource("plateau.txt").toURI();
            path = Paths.get(uri);
        }
        return path;
    }

    public static boolean isArgumentSpecified(final String[] args) {
        return args != null && args.length > 0 && !args[0].isEmpty() && isPathValid(args[0]);
    }

    public static boolean isPathValid(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException e) {
            return false;
        }
        return true;
    }
}
