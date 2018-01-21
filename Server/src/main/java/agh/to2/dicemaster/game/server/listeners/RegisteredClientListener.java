package agh.to2.dicemaster.game.server.listeners;

import agh.to2.dicemaster.common.CommunicationConstants;
import agh.to2.dicemaster.common.RequestType;
import agh.to2.dicemaster.common.api.MoveDTO;
import agh.to2.dicemaster.common.DTO.CreateGameRequestDTO;
import agh.to2.dicemaster.common.DTO.JoinGameRequestDTO;
import agh.to2.dicemaster.game.server.receivers.RegisteredClientReceiver;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisteredClientListener implements MessageListener {

    private final RegisteredClientReceiver registeredClientReceiver;
    private final MessageConverter messageConverter;

    @Autowired
    public RegisteredClientListener(RegisteredClientReceiver registeredClientReceiver, MessageConverter messageConverter) {
        this.registeredClientReceiver = registeredClientReceiver;
        this.messageConverter = messageConverter;
    }

    @Override
    public void onMessage(Message message) {
        String inQueueName = message.getMessageProperties().getConsumerQueue();
        RequestType requestType = RequestType.valueOf((String) message.getMessageProperties().getHeaders().get(CommunicationConstants.HEADER_REQUEST_TYPE));

//        TODO: check when this is called without the Request/Reply pattern
        String replyToQueueName = message.getMessageProperties().getReplyToAddress().getRoutingKey();

        switch (requestType){
            case LEAVE_GAME:
                registeredClientReceiver.onUserLeftGameRequest(inQueueName);
                break;
            case GET_AVAILABLE_GAMES:
                registeredClientReceiver.onGetAvailableGamesRequest(replyToQueueName);
                break;
            case CREATE_GAME:
                CreateGameRequestDTO createGameRequest = (CreateGameRequestDTO) messageConverter.fromMessage(message);
                registeredClientReceiver.onCreateGameRequest(createGameRequest.getGameConfigDTO(),createGameRequest.getUserType(), inQueueName, replyToQueueName);
                break;
            case JOIN_GAME:
                JoinGameRequestDTO joinGameRequest = (JoinGameRequestDTO) messageConverter.fromMessage(message);
                registeredClientReceiver.onJoinGameRequest(joinGameRequest.getGameDTO(), joinGameRequest.getUserType(), inQueueName, replyToQueueName);
                break;
            case MAKE_MOVE:
                MoveDTO move = (MoveDTO) messageConverter.fromMessage(message);
                registeredClientReceiver.onMoveRequest(move,inQueueName);
                break;
        }
    }
}