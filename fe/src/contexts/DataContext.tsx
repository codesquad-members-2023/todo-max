import { createContext, useContext, useState, ReactNode } from 'react';

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

type DataType = {
  todoListData: TodoItemType[] | null;
  setTodoListData: (value: TodoItemType[] | null) => void;
};

const DataContext = createContext<DataType>({
  todoListData: null,
  setTodoListData: () => {},
});

type DataProviderProps = {
  children: ReactNode;
};

export const DataProvider: React.FC<DataProviderProps> = ({ children }) => {
  const [todoListData, setTodoListData] = useState<TodoItemType[] | null>(null);

  return (
    <DataContext.Provider value={{ todoListData, setTodoListData }}>
      {children}
    </DataContext.Provider>
  );
};

export const useData = () => useContext(DataContext);
