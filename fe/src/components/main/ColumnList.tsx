import { useEffect, useRef } from 'react';
import styled from 'styled-components';
import { ColumnItem } from './ColumnItem';
import { FloatingActionBtn } from './FloatingAction';
import { useData } from '../../contexts/DataContext';

type TaskType = {
  taskId: number;
  title: string;
  contents: string;
  platform: string;
};

type TodoItemType = {
  processId: number;
  name: string;
  tasks: TaskType[];
};

type AddTaskType = TaskType & { processId: number };
type EditTaskType = { taskId: number; title: string; contents: string };

export const ColumnList = () => {
  const horizontalScrollRef = useRef<HTMLDivElement | null>(null);
  const { todoListData, setTodoListData } = useData();

  useEffect(() => {
    const fetchTodoList = async () => {
      const response = await fetch('/api');
      const todoData = await response.json();
      setTodoListData(todoData.message);
    };
    fetchTodoList();
  }, []);

  const handleNewTask = (newTask: AddTaskType) => {
    if (!todoListData) return;

    const updatedData = todoListData.map((item) =>
      item.processId === newTask.processId
        ? { ...item, tasks: [newTask, ...item.tasks] }
        : item,
    );

    setTodoListData(updatedData);
  };

  const handleTaskDelete = (taskId: number) => {
    if (!todoListData) return;

    const updatedData = todoListData.map((item) =>
      item.tasks.some((task) => task.taskId === taskId)
        ? {
            ...item,
            tasks: item.tasks.filter((task) => task.taskId !== taskId),
          }
        : item,
    );

    setTodoListData(updatedData);
  };

  const handleTitleChange = (newName: string, processId: number) => {
    if (!todoListData) return;

    const updatedData = todoListData.map((item) =>
      item.processId === processId ? { ...item, name: newName } : item,
    );

    setTodoListData(updatedData);
  };

  const handleNewColumn = () => {
    if (!todoListData) return;

    const newProcessId =
      Math.max(...todoListData.map((item) => item.processId)) + 1;

    const updatedData = [
      ...todoListData,
      {
        processId: newProcessId,
        name: '새 리스트',
        tasks: [],
      },
    ];

    setTodoListData(updatedData);
  };

  const handleColumnDelete = (processId: number) => {
    if (!todoListData) return;

    const updatedData = todoListData.filter(
      (item) => item.processId !== processId,
    );

    setTodoListData(updatedData);
  };

  const handleTaskEdit = (editedTask: EditTaskType) => {
    if (!todoListData) return;

    const updatedData = todoListData.map((item) =>
      item.tasks.some((task) => task.taskId === editedTask.taskId)
        ? {
            ...item,
            tasks: item.tasks.map((task) =>
              task.taskId === editedTask.taskId
                ? { ...task, ...editedTask }
                : task,
            ),
          }
        : item,
    );

    setTodoListData(updatedData);
  };

  const scrollHorizontally = (e: React.WheelEvent<HTMLDivElement>) => {
    if (horizontalScrollRef.current) {
      horizontalScrollRef.current.scrollLeft += e.deltaY;
    }
  };

  if (todoListData === null) {
    return <div>Loading...</div>;
  }

  return (
    <MainLayout>
      <ColumnLayout onWheel={scrollHorizontally} ref={horizontalScrollRef}>
        {todoListData.map((item: TodoItemType) => (
          <ColumnItem
            key={item.processId}
            title={item.name}
            tasks={item.tasks}
            processId={item.processId}
            onNewTask={handleNewTask}
            onTaskDelete={handleTaskDelete}
            onTitleChange={handleTitleChange}
            onColumnDelete={handleColumnDelete}
            onTaskEdit={handleTaskEdit}
          />
        ))}
      </ColumnLayout>
      <FloatingActionBtn onNewColumn={handleNewColumn} />
    </MainLayout>
  );
};

const MainLayout = styled.div`
  padding: 32px 80px;
  height: 85vh;

  background-color: ${({ theme: { colors } }) => colors.surfaceAlt};
`;

const ColumnLayout = styled.div`
  width: auto;
  display: flex;
  gap: 24px;
  height: 100%;
  overflow: hidden;
  overflow-x: auto;
`;
