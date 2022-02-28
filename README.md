# Install-Apk
The installation apk tool on the window OS.
# Use
## Window SendTo menu
> Test on window 10
- build installApk.bat file
```bat
java -jar %~dp0/InstallApk.jar %1

```
- `Win+R` input `shell:sendto` open SendTo folder & create installApk.bat shortcut in the folder\
![images](./images/folder.png)
- Test with any `.apk` file\
![images](./images/sendto.png)