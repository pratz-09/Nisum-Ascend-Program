# Todo List Application

## Overview
This is a simple Todo List application built with TypeScript. It allows users to manage their tasks efficiently by adding, removing, and toggling the completion status of todo items.

## Features
- Add new todo items
- Remove existing todo items
- Toggle the completion status of todo items
- Type safety through TypeScript interfaces and typecasting

## Project Structure
```
todo-list-app
├── src
│   ├── app.ts               # Entry point of the application
│   ├── models
│   │   └── Todo.ts          # Todo class definition
│   ├── interfaces
│   │   └── ITodo.ts         # Interface for Todo structure
│   ├── types
│   │   └── index.ts         # Type aliases and enums
│   └── utils
│       └── typeCasting.ts    # Utility functions for typecasting
├── package.json              # NPM configuration
├── tsconfig.json             # TypeScript configuration
└── README.md                 # Project documentation
```

## Setup Instructions
1. Clone the repository:
   ```
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```
   cd todo-list-app
   ```
3. Install the dependencies:
   ```
   npm install
   ```
4. Compile the TypeScript files:
   ```
   npx tsc
   ```
5. Run the application:
   ```
   node dist/app.js
   ```

## Usage
- To add a new todo item, use the appropriate method in the Todo class.
- To remove a todo item, call the remove method with the item's ID.
- To toggle the completion status, use the toggleCompletion method.

## Contributing
Feel free to submit issues or pull requests to improve the application.