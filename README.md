**Implement a multi-threaded file server that supports UPLOAD, DOWNLOAD, DELETE, and RENAME file operations.**
**Implementation** -
Implementation of the following was done:
  1. Upload
  2. Download
  3. Delete
  4. Rename

Server is established using RMI registry which can handle simultaneous calls from multiple clients.

There are four files involved in completing the above operations namely:
  1. **Client.java** - This file consists of the main interface for the user on the client end. The client looks up for the server’s host registry, gets the instance of the remote object using the name associated with it and then invokes appropriate methods.
  2. **ICommon.java** - Serves as a common interface being used in Client and Server.
  3. **Common.java** - This file implements the “ICommon.java” file and has the core logic/code of the different operations to be performed.
  4. **Server.java** - Server class is responsible for creating remote object, exporting it and then associating it with a name in RMI registry
