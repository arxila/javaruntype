Change Log
==========

2.0.0
-----

  * Added support for Java records.
  * Fixed type cache not properly clearing the key queue.
  * Fixed type definitions not being correctly parsed when type parameters included concrete classes.
  * Code cleanup.


1.3
---

  * Fixed StackOverflowError on Types.forJavaLangReflectType with self-referring bounds.
  * JavaDoc improvements.

1.2
---

  * Added functionality to obtain javaRuntype Types from java.lang.reflect.Type objects.

1.1
---

  * Removed all dependencies except "antlr-runtime"


1.0
---

  * First release of javaRuntype