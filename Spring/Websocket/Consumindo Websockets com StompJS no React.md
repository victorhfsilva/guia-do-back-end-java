# Consumindo WebSocket com React e @stomp/stompjs

Vamos criar um hook chamado `useWebSocket` para gerenciar a conexão WebSocket no frontend usando React e a biblioteca @stomp/stompjs.

## Instalação das Dependências

- Instale a biblioteca @stomp/stompjs:
    ```bash
    npm install @stomp/stompjs
    ```

## Criando o Hook `useWebSocket`

Crie um arquivo `useWebSocket.js` (ou `useWebSocket.ts` se estiver usando TypeScript) na pasta `src/hooks` do seu projeto React.

```javascript
import { useState } from 'react';
import { Client, StompSubscription, Message } from '@stomp/stompjs';

export const useWebSocket = () => {
    const [message, setMessage] = useState('');

    const getClient = async () : Promise<Client> => {
        return await new Promise<Client>((resolve, reject) => {
            const client = new Client({
                brokerURL: 'ws://localhost:8080/websocket-endpoint'
            });

            client.onConnect = () => {
                console.log(`Connected.`);
                resolve(client);
            };

            client.onStompError = frame => {
                reject(new Error(frame.body));
                console.error('Broker error: ' + frame.body);
            };

            client.activate();
        });
    };

    const subscribe = (client: Client, topic: string): StompSubscription => {
        return client.subscribe(`/topic/${topic}`, (message: Message) => {
            setMessage(message.body);
            console.log(`Subscribed to ${topic}.`);
            console.log(message.body);
        });
    };

    const unsubscribe = (subscription: StompSubscription) => {
        subscription.unsubscribe();
    };

    const publish = (client: Client, destination: string, body: string) => {
        client.publish({
            destination: `/app/${destination}`,
            body: body
        });
    };

    return {
        message,
        getClient,
        subscribe,
        unsubscribe,
        publish
    };
};
```

## Utilizando o Hook em um Componente React

Crie um componente React que utilize o hook `useWebSocket`.

```javascript
import { useState } from 'react';
import { useWebSocket } from '../hooks/useWebsocket';
import { Client, StompSubscription } from '@stomp/stompjs';

const WebSocketComponent = () => {
    const { message, getClient, subscribe, unsubscribe, publish } = useWebSocket();
    const [client, setClient] = useState<Client | null>(null);
    const [subscription, setSubscription] = useState<StompSubscription | null>(null);

    const handleSubscribe = async () => {
        const client = await getClient();
        setClient(client);
        const subscription = subscribe(client, 'greeting');
        setSubscription(subscription);     
    };

    const handleUnsubscribe = () => {
        if (subscription) {
            unsubscribe(subscription);
        }
    };

    const handleSendMessage = () => {
        if (client) {
            publish(client, 'hello', 'Victor');
        }
    };

    return (
        <div>
            <button onClick={handleSubscribe}>Subscribe</button>
            <button onClick={handleUnsubscribe}>Unsubscribe</button>
            <button onClick={handleSendMessage}>Send Message</button>
            <div>
                <h3>Messages:</h3>
                <p>{message}</p>
            </div>
        </div>
    );
};

export default WebSocketComponent;
```