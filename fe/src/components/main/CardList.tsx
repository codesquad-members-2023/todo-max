import React, { useState, useRef } from 'react';
import styled from 'styled-components';
import { Card } from '../card/Card';
import { Modal } from '../modal/Modal';
import { AddModeCard } from './AddModeCard';

type TaskType = {
  taskId: number;
  title: string;
  contents: string;
  platform: string;
};

type CardProps = {
  processId: number;
  tasks: TaskType[];
  isAddMode: boolean;
  onCancel: () => void;
  onNewTask: (newTask: AddTaskType) => void;
  onTaskDelete: (taskId: number) => void;
  onTaskEdit: (editedTask: EditTaskType) => void;
};

type AddTaskType = TaskType & { processId: number };
type EditTaskType = { taskId: number; title: string; contents: string };


export const CardList: React.FC<CardProps> = ({
  tasks,
  isAddMode,
  processId,
  onCancel,
  onNewTask,
  onTaskDelete,
  onTaskEdit,
}) => {
  const [isVisible, setIsVisible] = useState(false);
  const [currentTaskId, setCurrentTaskId] = useState<number | null>(null);

  const verticalScrollRef = useRef(null);

  const scrollVertically = (e: React.WheelEvent<HTMLDivElement>) => {
    if (e.currentTarget.scrollHeight > e.currentTarget.clientHeight) {
      e.stopPropagation();
    }
  };


  const modalHandler = (taskId: number): void => {
    setIsVisible((prevVisible) => !prevVisible);
    setCurrentTaskId(taskId);
  };

  const deleteHandler = async (taskId: number) => {
    const response = await fetch(`/api/task/${taskId}`, {
      method: 'DELETE',
    });
    const data = await response.json();
    console.log(data);

    setIsVisible((prevVisible) => !prevVisible);
    onTaskDelete(taskId);
  };

  const updateTaskViaPatch = async (
    taskId: number,
    title: string,
    body: string,
  ) => {
    const response = await fetch(`/api/task/${taskId}`, {
      method: 'PATCH',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ title, contents: body }),
    });

    if (!response.ok) {
      console.error('Error:', response.statusText);
      return;
    }

    // const updatedTask = await response.json();
    // console.log('Updated task:', updatedTask);
    onTaskEdit({ taskId, title, contents: body });
  };

  return (
    <CardListLayout
      onWheel={scrollVertically}
      ref={verticalScrollRef}
      className="layout"
    >
      {isAddMode && (
        <AddModeCard
          processId={processId}
          onCancel={onCancel}
          onNewTask={onNewTask}
        />
      )}
      {tasks.map((item: TaskType) => (
        <Card
          mode="default"
          key={item.taskId}
          title={item.title}
          contents={item.contents}
          platform={item.platform}
          modalHandler={() => modalHandler(item.taskId)}
          onEdit={(title, body) => updateTaskViaPatch(item.taskId, title, body)}
        />
      ))}
      {isVisible && (
        <Modal
          alertText="선택한 카드를 삭제할깝쇼?"
          onClose={() => {
            if (currentTaskId !== null) {
              modalHandler(currentTaskId);
            }
          }}
          onClick={() => {
            if (currentTaskId !== null) {
              deleteHandler(currentTaskId);
            }
          }}
        />
      )}
    </CardListLayout>
  );
};

export const CardListLayout = styled.div`
  display: flex;
  flex-direction: column;
  gap: 10px;
  height: 100%;
  overflow-y: auto;
  ::-webkit-scrollbar {
    display: none;
  }
`;
