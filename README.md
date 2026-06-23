MFK WindowTester Fork

=====================

Some basic info to save you time:
1. We have set the e4 branch to be the default intentionally.
2. The "root" pom lives in the com.windowtester_parent directory.
3. This repo doesn't complile as-is in the Eclipse IDE. Work could be done to close this gap (see description/comments on https://mfk.atlassian.net/browse/MCBRN-4384), but given the infrequent development here, we have decided the effort is not justified for now.
4. Instead, to build/compile, run the following command in a terminal from the root directory (include JAVA_HOME if you need to point to an explicit Java version):

   ```bash
   JAVA_HOME='/c/Program Files/Eclipse Adoptium/jdk-21.0.11.10-hotspot' mvn -f com.windowtester_parent/pom.xml clean verify

5. On successful build, a zip file gets created in the com.windowtester_repository/target folder (e.g. com.windowtester.repository-6.2.0.asy202606232051.zip).
6. This file can then be uploaded to the MFK p2 site. Details for the p2 site can be found on the MFK Wiki.
