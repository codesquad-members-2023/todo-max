import React, { useState } from 'react';
import { CardList } from './CardList';
import { ColumnTitle } from './ColumnTitle';

type TaskType = {
  taskId: number;
  title: string;
  contents: string;
  platform: string;
};

type ColumnItemProps = {
  processId: number;
  title: string;
  tasks: TaskType[];
  onNewTask: (newTask: AddTaskType) => void;
  onTaskDelete: (taskId: number) => void;
  onTitleChange: (newName: string, processId: number) => void;
  onColumnDelete: (processId: number) => void;
  onTaskEdit: (editedTask: EditTaskType) => void;
};

type AddTaskType = TaskType & { processId: number };
type EditTaskType = { taskId: number; title: string; contents: string };


export const ColumnItem: React.FC<ColumnItemProps> = ({
  processId,
  title,
  tasks,
  onNewTask,
  onTaskDelete,
  onTitleChange,
  onColumnDelete,
  onTaskEdit,
}) => {

  const numberOfTasks = tasks.length;
  const [isAddMode, setIsAddMode] = useState(false);

  const handleAddModeClick = () => {
    setIsAddMode(!isAddMode);
  };

  return (
    <div>
      <ColumnTitle
        title={title}
        numberOfTasks={numberOfTasks}
        onAddClick={handleAddModeClick}
        onTitleChange={onTitleChange}
        processId={processId}
        onColumnDelete={onColumnDelete}

      />

      <CardList
        processId={processId}
        tasks={tasks}
        isAddMode={isAddMode}
        onCancel={handleAddModeClick}
        onNewTask={onNewTask}
        onTaskDelete={onTaskDelete}
        onTaskEdit={onTaskEdit}
      />
    </div>
  );
};
