@echo off
set JLINK_VM_OPTIONS=
set DIR=%~dp0
"%DIR%\java" %JLINK_VM_OPTIONS% -m hi.verkefni3.vidmot.verkefni1/hi.verkefni4.vidmot.SnakurApplication %*
