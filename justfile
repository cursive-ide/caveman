# List available commands
default: help

help:
    @just --list

# Run the main program
run:
    clj -M:run

# Format all Clojure source files
format:
    clj -M:format fix

# Check if all Clojure source files are formatted correctly
format-check:
    clj -M:format check

# Run clj-kondo linter
lint:
    clj -M:lint

# Run both format check and lint
check: format-check lint
