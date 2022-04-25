package academico.domain.contracts;

import java.util.Collection;

public interface HasPermissions {
    Collection<String> getPermissions();
}
