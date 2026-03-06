# yy Chatbot User Guide

yy is a simple command-line chatbot that helps users manage tasks such as **todos, deadlines, and events**.  
It allows users to add tasks, mark tasks as done, delete tasks, and search tasks easily through commands.

---

# Getting Started

1. Run the chatbot program.
2. Type commands into the terminal.
3. The chatbot will respond and manage your tasks.

---

# Features

## View All Tasks

Shows all tasks currently stored in the list.

Command:


list


Example output:


[T][ ] read book

[D][ ] return book (by: Dec 02 2019)


---

## Add a Todo Task

Adds a task without a specific date.

Command:


todo DESCRIPTION


Example:


todo read book


Output:


Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list


---

## Add a Deadline Task

Adds a task with a due date.

Date format must be:


yyyy-MM-dd


Command:


deadline DESCRIPTION /by DATE


Example:


deadline return book /by 2019-12-02


Output:


[D][ ] return book (by: Dec 02 2019)


---

## Add an Event Task

Adds a task with a start and end time.

Command:


event DESCRIPTION /from START /to END


Example:


event meeting /from 2pm /to 4pm


Output:


[E][ ] meeting (from: 2pm to: 4pm)


---

## Mark a Task as Done

Marks a task as completed.

Command:


mark TASK_NUMBER


Example:


mark 2


Output:


Nice! I've marked this task as done:
[D][X] return book (by: Dec 02 2019)


---

## Unmark a Task

Marks a task as not done.

Command:


unmark TASK_NUMBER


Example:


unmark 2


Output:


OK, I've marked this task as not done yet:
[D][ ] return book (by: Dec 02 2019)


---

## Delete a Task

Removes a task from the task list.

Command:


delete TASK_NUMBER


Example:


delete 3


Output:


Noted. I've removed this task:
[T][ ] read book
Now you have 2 tasks in the list


---

## Find Tasks

Searches for tasks that contain a specific keyword.

Command:


find KEYWORD


Example:


find book


Output:


Here are the matching tasks in your list:

[T][X] read book

[D][ ] return book (by: Dec 02 2019)


---

## Exit the Chatbot

Closes the chatbot program.

Command:


bye


Output:


Bye! See you next time!


---

# Command Summary

| Command | Description |
|------|------|
| `list` | Show all tasks |
| `todo DESCRIPTION` | Add a todo task |
| `deadline DESCRIPTION /by DATE` | Add a deadline task |
| `event DESCRIPTION /from START /to END` | Add an event |
| `mark NUMBER` | Mark task as done |
| `unmark NUMBER` | Unmark task |
| `delete NUMBER` | Delete a task |
| `find KEYWORD` | Search tasks |
| `bye` | Exit the chatbot |