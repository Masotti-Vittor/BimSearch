#!/bin/bash

# Get repo directory
REPO_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# Install .desktop file
DESKTOP_FILE="$REPO_DIR/BimSearch.desktop"
TARGET_DESKTOP="$HOME/.local/share/applications/BimSearch.desktop"

cp "$DESKTOP_FILE" "$TARGET_DESKTOP"
chmod +x "$TARGET_DESKTOP"

# Create desktop shortcut
ln -sf "$TARGET_DESKTOP" "$HOME/Desktop/"

# Make scripts executable
chmod +x "$REPO_DIR/runApp.sh"

echo "✅ Installation complete! You can now:"
echo "- Launch from Application Menu (search 'Bim Search')"
echo "- Use the desktop shortcut"
