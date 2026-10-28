#!/bin/bash
# System report for student Goslinuly (Goslinuly, ID gosling)
STUDENT="Goslinuly"
GROUP="Goslinuly"
STUDENT_ID="gosling"
BASE_DIR="$(cd "$(dirname "$0")/.." && pwd)"
LOG_FILE="$BASE_DIR/logs/Goslinuly_system_report.log"

{
  echo "=================================================="
  echo " SYSTEM REPORT"
  echo "=================================================="
  echo "Student      : $STUDENT ($GROUP, ID $STUDENT_ID)"
  echo "Date & time  : $(date '+%Y-%m-%d %H:%M:%S %Z')"
  echo "Username     : $(whoami)"
  echo "OS           : $(grep PRETTY_NAME /etc/os-release | cut -d= -f2 | tr -d '"')"
  echo "Kernel       : $(uname -sr)"
  echo "--- CPU ---"
  lscpu | grep -E '^(Architecture|CPU\(s\)|Model name|Vendor ID)'
  echo "--- Memory ---"
  free -h
  echo "--- Disk usage ---"
  df -h /
  echo "--- Available disk space ---"
  df -h --output=avail / | tail -1 | xargs echo "Available on / :"
  echo
} | tee -a "$LOG_FILE"
