//object AkkActorSystemExample {
//
//}
//import akka.actor.typed.{ActorRef, ActorSystem, Behavior}
//import akka.actor.typed.scaladsl.Behaviors
//
//object MessageActors {
//  final case class MessageReceiver(message: String, messageRef: ActorRef[MessageCreator.Message])
//  def apply(): Behavior[MessageReceiver] = Behaviors.receive{ (context, message) =>
//    if(message.message == "finished") {
//      println("Finished communication !!")
//      Behaviors.stopped
//    } else {
//      println(message.message)
//      message.messageRef ! MessageCreator.Message("finished", context.self)
//      Behaviors.same
//    }
//  }
//}
//object MessageCreator {
//  final case class Message(m : String, receiverRef: ActorRef[MessageActors.MessageReceiver])
//
//  def apply(): Behavior[Message] =
//    Behaviors.setup { context =>
//      val messageActor = context.spawn(MessageActors(), "Message")
//      Behaviors.receiveMessage { message =>
//        if(message.m == "finished") {
//          println("Finished communication pinging receiver")
//          message.receiverRef ! MessageActors.MessageReceiver("finished", context.self)
//          Behaviors.stopped
//        } else {
//          messageActor ! MessageActors.MessageReceiver(message.m, context.self)
//          Behaviors.same
//        }
//
//      }
//    }
//}
//object SimpleActorExample extends App {
//
//
//
//  val system = ActorSystem(MessageCreator(),"SimpleSystem")
//
//  val messageReceiver = ActorSystem(MessageActors(), "MessageReceiver")
//
//  system ! MessageCreator.Message("Hi there", messageReceiver)
//
//  println(system.settings)
//
//}
