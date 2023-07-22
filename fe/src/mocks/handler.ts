import { rest } from 'msw';

import { todolist } from './data/todolist';
import { actionHistory } from './data/actionHistory';

const handlers = [
  rest.get('/api', (_, res, ctx) => {
    return res(ctx.status(200), ctx.json(todolist));
  }),

  rest.get('/api/history', (_, res, ctx) => {
    return res(ctx.status(200), ctx.json(actionHistory));
  }),

  rest.delete('/api/history', (_, res, ctx) => {
    // 모든 데이터를 삭제한 후의 상태를 반환
    actionHistory.message = [];
    return res(ctx.status(200), ctx.json(actionHistory));
  }),

  rest.delete('/api/task/:taskId', (req, res, ctx) => {
    const { taskId } = req.params;
    return res(
      ctx.status(200),
      ctx.json({
        statusCode: 200,
        message: `카드${taskId} 삭제 성공`,
      }),
    );
  }),

  rest.patch('/api/task/:taskId', (_, res, ctx) => {
    // const { taskId } = req.params;
    // const testBody = req.body;
    // console.log(testBody);

    return res(
      ctx.json({
        statusCode: 200,
        message: '카드 수정 성공',
      }),
    );
  }),

  rest.post('/api/task', (req, res, ctx) => {
    const newTask: any = req.body;
    const taskId = Math.floor(Math.random() * 1000) + 1;

    return res(
      ctx.json({
        statusCode: 200,
        message: {
          taskId: taskId,
          processId: newTask.processId,
          title: newTask.title,
          contents: newTask.contents,
          platform: newTask.platform,
        },
      }),
      ctx.status(200),
    );
  }),

  rest.patch('/api/process/:processId', (req, res, ctx) => {
    const { processId } = req.params;

    return res(
      ctx.json({
        statusCode: 200,
        message: `컬럼 ${processId} 업데이트 성공`,
      }),
      ctx.status(200),
    );
  }),

  rest.post('/api/process', (req, res, ctx) => {
    // req.body가 객체이며, processName 속성을 가지고 있는지 확인합니다.
    if (
      typeof req.body === 'object' &&
      req.body !== null &&
      'processName' in req.body
    ) {
      const newProcess: any = req.body.processName;

      return res(
        ctx.json({
          statusCode: 200,
          message: `컬럼 ${newProcess} 생성 성공`,
        }),
        ctx.status(200),
      );
    } else {
      // req.body가 예상한 형식이 아닌 경우에 대한 처리를 추가할 수 있습니다.
      return res(ctx.status(400), ctx.json({ error: 'Invalid request body' }));
    }
  }),

  rest.delete('/api/process/:processId', (req, res, ctx) => {
    const { processId } = req.params;

    return res(
      ctx.json({
        statusCode: 200,
        message: `컬럼 ${processId} 삭제 성공`,
      }),
      ctx.status(200),
    );
  }),
];

export default handlers;
