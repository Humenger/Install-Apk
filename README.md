# Install-Apk
The installation apk tool on the window OS.
# Features
- Support for multiple devices
- Multi-adb process conflict handling
- Support Apk path contains special characters
- More(todo)
# Use
## Window SendTo menu
> Tested only window 10
- Create the `installApk.bat` file in the directory where `installApk.jar` is located and enter the following content:
```bat
java -jar %~dp0/InstallApk.jar %1

```
> You need to configure the java environment first
- `Win+R` input `shell:sendto` open SendTo folder & create installApk.bat shortcut in the folder\
![images](./images/folder.png)
- Test with any `.apk` file\
![images](./images/sendto.png)
# Build environment
```
IntelliJ IDEA 2021.2 (Ultimate Edition)
Build #IU-212.4746.92, built on July 27, 2021
Runtime version: 11.0.11+9-b1504.13 amd64
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
Windows 10 10.0
GC: G1 Young Generation, G1 Old Generation
Memory: 2048M
Cores: 12
Kotlin: 212-1.5.10-release-IJ4746.92
```