import { useState, useEffect } from 'react';
import { Card } from '../card/Card';

type TaskType = {
  taskId: number;
  title: string;
  contents: string;
  platform: string;
};

type AddModeCardProps = {
  processId: number;
  onCancel: () => void;
  onNewTask: (newTask: AddTaskType) => void;
};

type AddTaskType = TaskType & { processId: number };

export const AddModeCard: React.FC<AddModeCardProps> = ({
  processId,
  onCancel,
  onNewTask,
}) => {
  const [isMobile, setIsMobile] = useState(false);

  useEffect(() => {
    const userAgent = window.navigator.userAgent;
    const mobile = /iPhone|iPad|iPod|Android/i.test(userAgent);
    setIsMobile(mobile);
  }, []);

  const handleSubmit = async (title: string, body: string) => {
    const response = await fetch('/api/task', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        processId: processId,
        title: title,
        contents: body,
        platform: isMobile ? 'mobile' : 'web',
      }),
    });

    const addCardData = await response.json();

    onNewTask(addCardData.message);
    onCancel();
  };

  return (
    <Card
      mode="addEdit"
      title="제목을 입력하세요"
      contents="내용을 입력하세요"
      onSubmit={handleSubmit}
      onCancel={onCancel}
    />
  );
};
